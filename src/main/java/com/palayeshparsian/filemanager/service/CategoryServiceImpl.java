package com.palayeshparsian.filemanager.service;

import com.palayeshparsian.filemanager.dao.CategoryDAO;
import com.palayeshparsian.filemanager.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryDAO.findById(id);
    }

    @Override
    public void save(Category category) {
        categoryDAO.save(category);
    }

    @Override
    public void delete(Category category) {
        categoryDAO.delete(category);
    }
}
