package vn.LeThanhTuan.service;

import vn.LeThanhTuan.entity.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    ProductDto updatedProduct(ProductDto productDto, Integer id);

    ProductDto getProductById(Integer id);

    List<ProductDto> getAllProduct();

    ProductDto enabledProductById(Integer id);

    ProductDto disabledProductById(Integer id);

    List<ProductDto> getAllProductByCategoryId(Integer cateId);
}
