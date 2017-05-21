package com.palayeshparsian.filemanager.service;

import com.palayeshparsian.filemanager.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Narges on 5/10/2017.
 */
public interface FileService {
    List<File> findAll();
    File findById(Long id);
    void save(File file, MultipartFile multFile);
    void delete(File file);
}
