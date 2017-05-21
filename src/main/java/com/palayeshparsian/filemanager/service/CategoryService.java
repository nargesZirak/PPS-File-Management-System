package com.palayeshparsian.filemanager.service;

import com.palayeshparsian.filemanager.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Long id);
    void save(Category category);
    void delete(Category category);
}
