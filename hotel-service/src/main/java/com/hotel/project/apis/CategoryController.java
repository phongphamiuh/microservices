package com.hotel.project.apis;

import com.hotel.project.dto.CategoryResponse;
import com.hotel.project.repository.CategoryRepository;
import com.hotel.project.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/alls")
    public ResponseEntity<List<CategoryResponse>> getAlls(){
        return ResponseEntity.ok().body(categoryService.getAlls());
    }
}
