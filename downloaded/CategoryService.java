package com.ptit.shopshoe.service;

import com.ptit.shopshoe.controller.request.CategoryRequest;
import com.ptit.shopshoe.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto getById(Integer id);
    List<CategoryDto> getAll();
    CategoryDto create(CategoryRequest categoryRequest);
    CategoryDto update(Integer id,CategoryRequest categoryRequest);
    Boolean delete(Integer id);
}
