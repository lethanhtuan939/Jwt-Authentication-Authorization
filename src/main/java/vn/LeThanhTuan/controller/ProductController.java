package vn.LeThanhTuan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import vn.LeThanhTuan.entity.dto.ProductDto;
import vn.LeThanhTuan.respone.ResponeObject;
import vn.LeThanhTuan.service.ProductService;
import vn.LeThanhTuan.util.FileUtil;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<ResponeObject> getAllProduct( @RequestParam(value = "keyword", defaultValue = "") String keyword,
                                                        @RequestParam(value = "p", defaultValue = "1") int pageIndex) {
        Page<ProductDto> page = productService.getAllProduct(keyword, pageIndex, 5);
        List<ProductDto> products = page.getContent();

        return ResponseEntity.status(HttpStatus.OK).body(ResponeObject.builder()
                                            .status(HttpStatus.OK.name())
                                            .message("Successfully!")
                                            .data(products)
                                            .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponeObject> getProductById(@PathVariable Integer id) {
        ProductDto product = productService.getProductById(id);

        return ResponseEntity.status(HttpStatus.OK).body(ResponeObject.builder()
                                            .status(HttpStatus.OK.name())
                                            .message("Successfully!")
                                            .data(product)
                                            .build());
    }

    @PostMapping
    public ResponseEntity<ResponeObject> createProduct(@RequestParam("data") String json, @RequestParam("file") MultipartFile multipartFile) throws IOException {

        ProductDto productDto = new ObjectMapper().readValue(json, ProductDto.class);
        ProductDto product = productService.createProduct(productDto, multipartFile);

        return ResponseEntity.status(HttpStatus.OK).body(ResponeObject.builder()
                                            .status(HttpStatus.OK.name())
                                            .message("Successfully!")
                                            .data(product)
                                            .build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponeObject> updatedProduct(@RequestParam("data") String json, @RequestParam("file") MultipartFile multipartFile, @PathVariable Integer id) throws IOException {
        ProductDto productDto = new ObjectMapper().readValue(json, ProductDto.class);
        ProductDto product = productService.updatedProduct(productDto, id, multipartFile);

        return ResponseEntity.status(HttpStatus.OK).body(ResponeObject.builder()
                                            .status(HttpStatus.OK.name())
                                            .message("Update Successfully!")
                                            .data(product)
                                            .build());
    }

    @DeleteMapping("/enabled/{id}")
    public ResponseEntity<ResponeObject> enabledProduct(@PathVariable Integer id) {
        ProductDto product = productService.enabledProductById(id);

        return ResponseEntity.status(HttpStatus.OK).body(ResponeObject.builder()
                                            .status(HttpStatus.OK.name())
                                            .message("Successfully!")
                                            .data(product)
                                            .build());
    }

    @DeleteMapping("/disabled/{id}")
    public ResponseEntity<ResponeObject> disabledProduct(@PathVariable Integer id) {
        ProductDto product = productService.disabledProductById(id);

        return ResponseEntity.status(HttpStatus.OK).body(ResponeObject.builder()
                                            .status(HttpStatus.OK.name())
                                            .message("Successfully!")
                                            .data(product)
                                            .build());
    }

    @GetMapping("/category/{id}/product")
    public ResponseEntity<ResponeObject> getAllProductByCategoryId(@PathVariable("id") Integer cateId) {
        List<ProductDto> products = productService.getAllProductByCategoryId(cateId);

        return ResponseEntity.status(HttpStatus.OK).body(ResponeObject.builder()
                                            .status(HttpStatus.OK.name())
                                            .message("Successfully!")
                                            .data(products)
                                            .build());
    }

    @GetMapping("/file/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        try {
            byte[] bytes = FileUtil.readFileContent(fileName);
            return  ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/files")
    public ResponseEntity<ResponeObject> getUploadFiles() {
        try {
            List<String> urls = FileUtil.loadAll()
                    .map(path -> {
                        String urlPath = MvcUriComponentsBuilder.fromMethodName(ProductController.class, "readDetailFile", path.getFileName().toString()).build().toUri().toString();
                        return urlPath;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(ResponeObject.builder()
                            .status(HttpStatus.OK.name())
                            .message("List files successfully!")
                            .data(urls)
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(ResponeObject.builder()
                    .status(HttpStatus.BAD_REQUEST.name())
                    .message("List files failed!")
                    .data(new String[] {})
                    .build());
        }
    }

}