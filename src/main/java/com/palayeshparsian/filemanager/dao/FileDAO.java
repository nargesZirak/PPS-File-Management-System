package com.palayeshparsian.filemanager.dao;


import com.palayeshparsian.filemanager.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileDAO {
    List<File> findAll();
    File findById(Long id);
    void save(File file);
    void delete(File file);
}
