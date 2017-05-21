package com.palayeshparsian.filemanager.dao;

import com.palayeshparsian.filemanager.model.Category;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    @SuppressWarnings("unchecked")
    public List<Category> findAll() {
        Session session = sessionFactory.openSession();
        // Get all categories with a Hibernate criteria
        List<Category> allCategories = session.createCriteria(Category.class).list() ;
        session.close();
        return allCategories;
    }

    @Override
    public Category findById(Long id) {
        Session session = sessionFactory.openSession();
        Category category = session.get(Category.class,id);
        Hibernate.initialize(category.getFiles());
        session.close();
        return category;
    }

    @Override
    public void save(Category category) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(category);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Category category) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(category);
        session.getTransaction().commit();
        session.close();
    }
}
