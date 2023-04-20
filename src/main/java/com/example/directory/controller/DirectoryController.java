package com.example.directory.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.directory.DirectoryDto;
import com.example.directory.ServiceImpl.DirectoryServiceImpl;
import com.example.directory.dao.ContactRepository;
import com.example.directory.entity.Address;
import com.example.directory.entity.Directory;
import com.example.directory.service.ContactService;
import com.example.directory.service.DirectoryService;

@RestController
@RequestMapping("/directory")
public class DirectoryController {

    
    DirectoryService directoryServiceImpl;
   
    
    
    public DirectoryController() {
    }


	@Autowired
    public DirectoryController(DirectoryService directoryServiceImpl) {
    	this.directoryServiceImpl=directoryServiceImpl;
    	
    }
    
	//----------------------------find all directories
	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public List<Directory> getDirectories() {
        return directoryServiceImpl.getDirectories();
    }
    
    //----------------------------find directory by id
	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all/{directoryId}")
    public DirectoryDto findById(@PathVariable("directoryId") int directoryId){

        return directoryServiceImpl.findById(directoryId);
    }
    
	
    //----------------------------delete directory by id
	@CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/all/{directoryId}")
    public void deleteById(@PathVariable int directoryId){
        directoryServiceImpl.deleteById(directoryId);
        System.out.println("---------------------------deleting");
    }
    
    //----------------------------create directory
	@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/all")
    public Directory saveDirectory(@RequestBody Directory tempDirectory){
    	tempDirectory.setDirectoryId(0);
        return directoryServiceImpl.saveDirectory(tempDirectory);
    }
    
    //----------------------------update directory
	@CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/all/put/{directoryId}")
    public Directory updateContact(@RequestBody Directory tempDirectory,@PathVariable int directoryId){

		Directory updatedDir=directoryServiceImpl.updateUser(directoryId, tempDirectory);
		System.out.println("--------------------updatedUser");
		System.out.println(updatedDir);
		return updatedDir;
	}

	//-------------------------------------search 
	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("all/search/{instance}")
    public List<Directory> search(@PathVariable String instance){
    	
    	try {
    		return directoryServiceImpl.search(instance);
    	} catch (IllegalArgumentException e) {
    		return null;
    	}	
    }
			
	
}
