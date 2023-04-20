package com.example.directory.controller;

import java.rmi.ServerException;
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

import com.example.directory.ServiceImpl.ContactServiceImpl;
import com.example.directory.dao.ContactRepository;
import com.example.directory.entity.Contact;
import com.example.directory.entity.Directory;
import com.example.directory.entity.Email;
import com.example.directory.service.ContactService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/contacts")
public class ContactController {
	
    private ContactService contactServiceImpl;
//    private ContactRepository contactRepo;
    
    
    public ContactController() {
		
	}

	@Autowired
    public ContactController(ContactService contactServiceImpl){
        this.contactServiceImpl=contactServiceImpl;
        
    }
	
	
	//----------------------------find all contacts
    @GetMapping("/all")
    public List<Contact> findAll(){
        return contactServiceImpl.getContacts();
    }
    
    
    //----------------------------find contact by id
    @GetMapping("/all/{contactId}")
    public Contact findById(@PathVariable("contactId") int contactId){
        return contactServiceImpl.findById(contactId);
    }
    
    
    //----------------------------create contact
    @PostMapping("/all")
    public Contact createContact(@RequestBody Contact tempContact){
    	tempContact.setContactId(0);
    	Contact  theContact= contactServiceImpl.saveContact(tempContact);
        	return theContact;
    }
    
    
    //----------------------------delete contact by id
    @DeleteMapping("/all/{contactId}")
    public void deleteById(@PathVariable int contactId){
        contactServiceImpl.deleteById(contactId);
    }
    
    //----------------------------update contact 
    @PutMapping("all")
    public Contact updateContact(@RequestBody Contact tempContact){
    	Contact  theContact= contactServiceImpl.saveContact(tempContact);
    	try{
        	return theContact;
        }
        catch(Exception e){
        	return null;
        }
    }
    
    //-----------------------------------find by user id
    @GetMapping("all/user/{dirId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Contact> findByUser(@PathVariable("dirId") int directory){
    	return contactServiceImpl.findContactByUserDirectory(directory);
    }
   
}
