package vn.LeThanhTuan.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import vn.LeThanhTuan.entity.Category;
import vn.LeThanhTuan.entity.Product;
import vn.LeThanhTuan.entity.dto.ProductDto;
import vn.LeThanhTuan.exception.ResourceNotFoundException;
import vn.LeThanhTuan.repository.CategoryRepository;
import vn.LeThanhTuan.repository.ProductRepository;
import vn.LeThanhTuan.service.ProductService;
import vn.LeThanhTuan.util.FileUtil;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    private ProductDto toDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    private Product toProduct(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto, MultipartFile multipartFile) throws IOException {
        Product product = toProduct(productDto);
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", productDto.getCategoryId()));

        String fileName = FileUtil.generatedFileName(multipartFile);

        product.setCategory(category);
        product.setActive(true);
        product.setImage(fileName);

        Product savedProduct = productRepository.save(product);

        FileUtil.saveFile(fileName, multipartFile);

        return toDto(savedProduct);
    }

    @Override
    public ProductDto updatedProduct(ProductDto productDto, Integer id, MultipartFile file) throws IOException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", productDto.getCategoryId()));

        String fileName = FileUtil.generatedFileName(file);
        if(!file.isEmpty()) {
            product.setImage(fileName);
            FileUtil.saveFile(fileName, file);
        }

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(category);

        Product updatedProduct = productRepository.save(product);

        return toDto(updatedProduct);
    }

    @Override
    public ProductDto getProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        return toDto(product);
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto enabledProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        product.setActive(true);
        Product enabledProduct = productRepository.save(product);

        return toDto(enabledProduct);
    }

    @Override
    public ProductDto disabledProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        product.setActive(false);
        Product enabledProduct = productRepository.save(product);

        return toDto(enabledProduct);
    }

    @Override
    public List<ProductDto> getAllProductByCategoryId(Integer cateId) {
        List<Product> products = productRepository.findAllByCategoryId(cateId);

        return products.stream().map(this::toDto).collect(Collectors.toList());
    }
}
