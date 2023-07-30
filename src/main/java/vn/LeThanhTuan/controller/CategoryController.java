package vn.LeThanhTuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.LeThanhTuan.entity.dto.CategoryDto;
import vn.LeThanhTuan.respone.ResponeObject;
import vn.LeThanhTuan.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ResponeObject> getAllCategories() {
        try {
            List<CategoryDto> categories = categoryService.getAllCategories();

            return ResponseEntity.status(HttpStatus.OK).body(ResponeObject.builder()
                            .status(HttpStatus.OK.name())
                            .message("Successfully!")
                            .data(categories)
                            .build());
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.OK).body(ResponeObject.builder()
                    .status(HttpStatus.NOT_FOUND.name())
                    .message("Failed to get!")
                    .data(null)
                    .build());
        }
    }

    @PostMapping()
    public ResponseEntity<ResponeObject> createCategory(@RequestBody CategoryDto categoryDto) {
        try {
            CategoryDto savedCategory = categoryService.createCategory(categoryDto);

            return ResponseEntity.status(HttpStatus.OK).body(ResponeObject.builder()
                            .status(HttpStatus.OK.name())
                            .message("Successfully!")
                            .data(savedCategory)
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.OK).body(ResponeObject.builder()
                    .status(HttpStatus.NOT_FOUND.name())
                    .message("Failed to get!")
                    .data(null)
                    .build());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponeObject> getCategoryById(@PathVariable Integer id) {
        try {
            CategoryDto category = categoryService.getCategoryById(id);

            return ResponseEntity.status(HttpStatus.OK).body(ResponeObject.builder()
                    .status(HttpStatus.OK.name())
                    .message("Successfully!")
                    .data(category)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.OK).body(ResponeObject.builder()
                    .status(HttpStatus.NOT_FOUND.name())
                    .message("Failed to get!")
                    .data(null)
                    .build());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponeObject> updatedCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer id) {
        try {
            CategoryDto category = categoryService.updateCategory(categoryDto, id);

            return ResponseEntity.status(HttpStatus.OK).body(ResponeObject.builder()
                    .status(HttpStatus.OK.name())
                    .message("Successfully!")
                    .data(category)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.OK).body(ResponeObject.builder()
                    .status(HttpStatus.NOT_FOUND.name())
                    .message("Failed to get!")
                    .data(null)
                    .build());
        }
    }

    @DeleteMapping("/enabled/{id}")
    public ResponseEntity<ResponeObject> enabledCategory(@PathVariable Integer id) {

        try {
            CategoryDto category = categoryService.enabledCategory(id);

            return ResponseEntity.status(HttpStatus.OK).body(ResponeObject.builder()
                    .status(HttpStatus.OK.name())
                    .message("Successfully!")
                    .data(category)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.OK).body(ResponeObject.builder()
                    .status(HttpStatus.NOT_FOUND.name())
                    .message("Failed to get!")
                    .data(null)
                    .build());
        }
    }

    @DeleteMapping("/disabled/{id}")
    public ResponseEntity<ResponeObject> disabledCategory(@PathVariable Integer id) {
        try {
            CategoryDto category = categoryService.disabledCategory(id);

            return ResponseEntity.status(HttpStatus.OK).body(ResponeObject.builder()
                    .status(HttpStatus.OK.name())
                    .message("Successfully!")
                    .data(category)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.OK).body(ResponeObject.builder()
                    .status(HttpStatus.NOT_FOUND.name())
                    .message("Failed to get!")
                    .data(null)
                    .build());
        }
    }
}
