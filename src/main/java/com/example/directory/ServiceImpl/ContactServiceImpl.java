package com.example.directory.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.directory.dao.ContactRepository;
import com.example.directory.entity.Contact;
import com.example.directory.entity.Directory;
import com.example.directory.entity.Email;
import com.example.directory.service.ContactService;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Service
public  class ContactServiceImpl implements ContactService {

    
    private ContactRepository contactRepository;
    
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public ContactServiceImpl() {
		super();
	}

    
	@Autowired
    public ContactServiceImpl(ContactRepository contactRepository){
        this.contactRepository=contactRepository;
      
    }

	//----------------------------find all contacts
    @Override
    public List<Contact> getContacts(){
        return contactRepository.findAll();
    }

    //----------------------------find contact by id
	@Override
    public Contact findById(int contactId){
		
		Optional<Contact> tempContact=contactRepository.findById(contactId);
		Contact theContact = null;
		if( tempContact.isPresent() ) {
			theContact=tempContact.get(); 
		}
		else {
            throw new RuntimeException("Did not find the contact with id - " + contactId);
        }
        return theContact;
    }

	//----------------------------delete contact by id
    @Override
    public void deleteById(int contactId){
    	Optional<Contact> tempContact=contactRepository.findById(contactId);
		Contact theContact = null;
		if( tempContact.isPresent() ) {
			theContact=tempContact.get();
		}
		else {
            throw new RuntimeException("Did not find the contact with id - " + contactId);
        }
        contactRepository.deleteById(contactId);
    }

    //-----------------------------save or delete by id
    @Override
    public Contact saveContact(Contact tempContact){
    	Contact theContact=contactRepository.save(tempContact);
        return theContact;
    }
    
    //-----------------------------------------------custom method implementation
    @Override
    public List<Contact> findContactByUserDirectory(int directory){
    	String hql = "FROM Contact as  e WHERE e.directory=:dirId";
        TypedQuery<Contact> query = entityManager.createQuery(hql, Contact.class);
        query.setParameter("dirId", directory);
        List<Contact> theContacts =query.getResultList();
        return theContacts;
    }
    	
}



