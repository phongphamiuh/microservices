package com.hotel.project.service;

import com.hotel.project.dto.CategoryResponse;
import com.hotel.project.model.entity.Category;
import com.hotel.project.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements  CategoryService{
    private final CategoryRepository categoryRepository;
    @Override
    public List<CategoryResponse> getAlls() {
        List<CategoryResponse> result=new ArrayList<>();
        List<Category> list=categoryRepository.findAll();
        list.forEach(category -> {
            result.add(CategoryResponse.builder().name(category.getName()).build());
        });
        return result;
    }
}
