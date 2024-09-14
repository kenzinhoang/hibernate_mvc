package org.example.modules.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class FileModel {
    @Autowired
    private FileRepository fileRepository;

    public FileCustom create(FileCustom file) throws SQLException {
        return fileRepository.save(file);
    }

    public List<FileCustom> findAll() throws SQLException {
        return fileRepository.findAll();
    }
}
