package com.palayeshparsian.filemanager.service;

import com.palayeshparsian.filemanager.dao.FileDAO;
import com.palayeshparsian.filemanager.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDAO fileDAO;

    @Override
    public List<File> findAll() {
        return fileDAO.findAll();
    }

    @Override
    public File findById(Long id) {
        return fileDAO.findById(id);
    }

    @Override
    public void save(File file, MultipartFile multiFile) {
        try {
            file.setBytes(multiFile.getBytes());
            fileDAO.save(file);
        } catch (IOException e) {
            System.err.println("Incapable of getting the file's byte array ");
        }
    }

    @Override
    public void delete(File file) {
        fileDAO.delete(file);
    }
}

