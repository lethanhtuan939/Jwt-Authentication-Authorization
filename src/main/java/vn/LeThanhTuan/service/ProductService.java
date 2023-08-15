package vn.LeThanhTuan.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import vn.LeThanhTuan.entity.dto.ProductDto;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto, MultipartFile multipartFile) throws IOException;

    ProductDto updatedProduct(ProductDto productDto, Integer id, MultipartFile file) throws IOException;

    ProductDto getProductById(Integer id);

    Page<ProductDto> getAllProduct(String keyword, int pageNumber, int amountPage);

    ProductDto enabledProductById(Integer id);

    ProductDto disabledProductById(Integer id);

    List<ProductDto> getAllProductByCategoryId(Integer cateId);
}
