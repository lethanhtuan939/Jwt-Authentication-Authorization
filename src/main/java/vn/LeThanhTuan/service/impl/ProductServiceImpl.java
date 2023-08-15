package vn.LeThanhTuan.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @CachePut(value = "products", key = "#id")
    public ProductDto updatedProduct(ProductDto productDto, Integer id, MultipartFile file) throws IOException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", productDto.getCategoryId()));

        if(!file.isEmpty()) {
            String fileName = FileUtil.generatedFileName(file);
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
    @Cacheable(value = "product", key = "#id")
    public ProductDto getProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        return toDto(product);
    }

    @Override
    @Cacheable(value = "products")
    public Page<ProductDto> getAllProduct(String keyword, int pageNumber, int amountPage) {
        Sort sort = Sort.by("id");
        Pageable pageable = PageRequest.of(pageNumber-1, amountPage, sort);

        Page<Product> products;
        if(keyword.isEmpty()) {
            products = productRepository.findAll(pageable);
        } else {
            products = productRepository.findAllActiveProduct(keyword, pageable);
        }

        return products.map(this::toDto);
    }

    @Override
    @Cacheable("product")
    public ProductDto enabledProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        product.setActive(true);
        Product enabledProduct = productRepository.save(product);

        return toDto(enabledProduct);
    }

    @Override
    @CacheEvict(value = "product", key = "#id")
    public ProductDto disabledProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        product.setActive(false);
        Product disabledProduct = productRepository.save(product);

        return toDto(disabledProduct);
    }

    @Override
    @Cacheable(value = "products")
    public List<ProductDto> getAllProductByCategoryId(Integer cateId) {
        List<Product> products = productRepository.findAllByCategoryId(cateId);

        return products.stream().map(this::toDto).collect(Collectors.toList());
    }
}
