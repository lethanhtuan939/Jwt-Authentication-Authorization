package vn.LeThanhTuan.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.LeThanhTuan.entity.Category;
import vn.LeThanhTuan.entity.dto.CategoryDto;
import vn.LeThanhTuan.exception.ResourceNotFoundException;
import vn.LeThanhTuan.repository.CategoryRepository;
import vn.LeThanhTuan.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    private CategoryDto toDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }

    private Category toCategory(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        categoryDto.setActive(true);
        Category category = toCategory(categoryDto);
        Category savedCategory = categoryRepository.save(category);

        return toDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        category.setName(categoryDto.getName());
        category.setActive(categoryDto.isActive());
        Category updatedCategory = categoryRepository.save(category);

        return toDto(updatedCategory);
    }

    @Override
    public CategoryDto enabledCategory(Integer id) {
        Category category = categoryRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        category.setActive(true);
        Category enabledCategory = categoryRepository.save(category);

        return toDto(enabledCategory);
    }

    @Override
    public CategoryDto disabledCategory(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        category.setActive(false);
        Category enabledCategory = categoryRepository.save(category);

        return toDto(enabledCategory);
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        return toDto(category);
    }

}
