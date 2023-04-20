package com.example.directory.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.directory.ServiceImpl.EmailServiceImpl;
import com.example.directory.entity.Directory;
import com.example.directory.entity.Email;
import com.example.directory.service.EmailService;

@RestController
@RequestMapping("/emails")
public class EmailController {
    
    private EmailService emailService;
    

    
//    public EmailController() {
//		super();
//	}
    @Autowired
	public EmailController(EmailService emailService){
        this.emailService=emailService;
    }

    //----------------------------find all emails
    
    @GetMapping("/all")
    public List<Email> getEmails(){
       
        try{
        	return emailService.findAll();
        }
        catch(Exception e){
        	return null;
        }
    }

    //----------------------------find email by id
    @GetMapping("/all/{id}")
    public Email findById(@PathVariable("id") int emailId)
    {
        return emailService.findById(emailId);

    }
    
    //----------------------------create email
	@PostMapping("/all")
	public Email saveEmail(@RequestBody Email tempEmail){
		tempEmail.setEmailId(0);
	    return emailService.saveEmail(tempEmail);
	}
	
	//----------------------------delete email by id
    @DeleteMapping("/all/{emailId}")
    public void deleteById(@PathVariable("emailId") int emailId){
        emailService.deleteById(emailId);
    }
    
    //----------------------------update address
    @PutMapping("/all")
    public Email updateContact(@RequestBody Email tempEmail){
    	Email  theEmail= emailService.saveEmail(tempEmail);
    	try{
        	return theEmail;
        }
        catch(Exception e){
        	return null;
        }
    }
    
    //-----------------------------------find by user id
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all/user/{dirId}")
    public List<Email> findByUser(@PathVariable("dirId") int directory){
    	return emailService.findEmailByUserDirectory(directory);
    }
 
}