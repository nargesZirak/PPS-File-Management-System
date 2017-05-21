package com.palayeshparsian.filemanager.dao;

import com.palayeshparsian.filemanager.model.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> findAll();
    Category findById(Long id);
    void save(Category category);
    void delete(Category category);
}
