package com.palayeshparsian.filemanager.dao;

import com.palayeshparsian.filemanager.model.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Repository
public class FileDAOImpl implements FileDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<File> findAll() {
        Session session = sessionFactory.openSession();
        List<File> files = session.createCriteria(File.class).list();
        session.close();
        return files;
    }

    @Override
    public File findById(Long id) {
        Session session = sessionFactory.openSession();
        File file = session.get(File.class,id);
        session.close();
        return file;
    }

    @Override
    public void save(File file) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(file);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(File file) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(file);
        session.getTransaction().commit();
        session.close();
    }
}
