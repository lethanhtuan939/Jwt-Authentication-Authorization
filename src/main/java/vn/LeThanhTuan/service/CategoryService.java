package vn.LeThanhTuan.service;

import vn.LeThanhTuan.entity.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();

    CategoryDto createCategory(CategoryDto category);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer id);

    CategoryDto enabledCategory(Integer id);

    CategoryDto disabledCategory(Integer id);

    CategoryDto getCategoryById(Integer id);
}
