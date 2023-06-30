package vn.LeThanhTuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.LeThanhTuan.entity.dto.CategoryDto;
import vn.LeThanhTuan.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories();

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.createCategory(categoryDto);

        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id) {
        CategoryDto category = categoryService.getCategoryById(id);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDto> updatedCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer id) {
        CategoryDto category = categoryService.updateCategory(categoryDto, id);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/enabled/{id}")
    public ResponseEntity<CategoryDto> enabledCategory(@PathVariable Integer id) {
        CategoryDto category = categoryService.enabledCategory(id);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/disabled/{id}")
    public ResponseEntity<CategoryDto> disabledCategory(@PathVariable Integer id) {
        CategoryDto category = categoryService.disabledCategory(id);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
