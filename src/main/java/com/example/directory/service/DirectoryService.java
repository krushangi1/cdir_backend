package com.example.directory.service;

import java.util.List;

import com.example.directory.DirectoryDto;
import com.example.directory.entity.Address;
import com.example.directory.entity.Directory;

public interface DirectoryService {
    public List<Directory> getDirectories();
    public DirectoryDto findById(int directoryId);
    public void deleteById(int directoryId);
    public Directory saveDirectory(Directory directoryList);
    public List<Directory> search(String instance);
    public Directory updateUser(Integer id, Directory userDetails);
    //custom
    //public List<Directory> findByUser(int directoryId);
}
