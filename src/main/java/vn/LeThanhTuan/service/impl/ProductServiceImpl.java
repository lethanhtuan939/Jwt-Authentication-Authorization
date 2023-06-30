package vn.LeThanhTuan.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.LeThanhTuan.entity.Category;
import vn.LeThanhTuan.entity.Product;
import vn.LeThanhTuan.entity.dto.ProductDto;
import vn.LeThanhTuan.exception.ResourceNotFoundException;
import vn.LeThanhTuan.repository.CategoryRepository;
import vn.LeThanhTuan.repository.ProductRepository;
import vn.LeThanhTuan.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

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
    public ProductDto createProduct(ProductDto productDto) {
        Product product = toProduct(productDto);
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", productDto.getCategoryId()));

        product.setCategory(category);
        product.setActive(true);
        Product savedProduct = productRepository.save(product);

        return toDto(savedProduct);
    }

    @Override
    public ProductDto updatedProduct(ProductDto productDto, Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", productDto.getCategoryId()));

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setActive(product.isActive());
        product.setImage(productDto.getImage());
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
