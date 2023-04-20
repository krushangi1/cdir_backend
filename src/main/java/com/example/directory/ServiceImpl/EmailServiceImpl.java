package com.example.directory.ServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.directory.dao.EmailRepository;
import com.example.directory.entity.Address;
import com.example.directory.entity.Directory;
import com.example.directory.entity.Email;
import com.example.directory.service.EmailService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Service
public  class EmailServiceImpl implements EmailService {


    private EmailRepository emailRepository;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public EmailServiceImpl() {
		super();
	}
    
    @Autowired
    public EmailServiceImpl(EmailRepository theEmailRepository){
        emailRepository = theEmailRepository;
    }
    
  //----------------------------find all email 
	@Override
    public List<Email> findAll(){
        return emailRepository.findAll();
    }

	//----------------------------find email by id
    @Override
    public Email findById(int emailId){
        Optional<Email> tempEmail = emailRepository.findById(emailId);
        Email theEmail = null;

        if (tempEmail.isPresent()) {
            theEmail = tempEmail.get();
        }
        else {
            throw new RuntimeException("Did not find the email with id - " + emailId);
        }
        return theEmail;
    }
    
    //----------------------------save or update email 
	  @Override
	  public Email saveEmail(Email tempEmail){
	
	      return emailRepository.save(tempEmail);
	  }
	  
	  
	//----------------------------delete email by id
    @Override
    public void deleteById(int emailId){
    	 Optional<Email> tempEmail = emailRepository.findById(emailId);
         Email theEmail = null;

         if (tempEmail.isPresent()) {
             theEmail = tempEmail.get();
         }
         else {
             throw new RuntimeException("Did not find the email with id - " + emailId);
         }
         emailRepository.deleteById(emailId);
    }

    
    //-----------------------------------------------custom method implementation
    public List<Email> findEmailByUserDirectory(int directoryId) {
    	String hql = "FROM Email as  e WHERE e.directory=:directoryId";
        TypedQuery<Email> query = entityManager.createQuery(hql, Email.class);
        query.setParameter("directoryId", directoryId);
        List<Email> theEmail =query.getResultList();
        return theEmail;
//        return emailRepository.findByDirectoryId(directoryId);
      }

}

	