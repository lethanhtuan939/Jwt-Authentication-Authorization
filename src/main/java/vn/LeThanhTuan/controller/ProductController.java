package vn.LeThanhTuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.LeThanhTuan.entity.dto.ProductDto;
import vn.LeThanhTuan.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAllProduct() {
        List<ProductDto> products = productService.getAllProduct();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Integer id) {
        ProductDto product = productService.getProductById(id);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto product = productService.createProduct(productDto);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updatedProduct(@RequestBody ProductDto productDto, @PathVariable Integer id) {
        ProductDto product = productService.updatedProduct(productDto, id);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/enabled/{id}")
    public ResponseEntity<ProductDto> enabledProduct(@PathVariable Integer id) {
        ProductDto product = productService.enabledProductById(id);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/disabled/{id}")
    public ResponseEntity<ProductDto> disabledProduct(@PathVariable Integer id) {
        ProductDto product = productService.disabledProductById(id);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProductByCategoryId(@RequestParam("category") Integer cateId) {
        List<ProductDto> products = productService.getAllProductByCategoryId(cateId);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}