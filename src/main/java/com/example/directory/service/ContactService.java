package com.example.directory.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.example.directory.entity.Address;
import com.example.directory.entity.Contact;
import com.example.directory.entity.Directory;

public interface ContactService extends ContactRepo{
    public List<Contact> getContacts();
    public Contact findById(int contactId);
    public void deleteById(int contactId);
    public Contact saveContact(Contact tempContact);
   
}
 interface ContactRepo {

//	public List<Address> findAddressByUserDirectory(int directory);
//	public List<Contact> findContactByUserDirectory(String phoneNo);
//	public List<Email> findEmailByUserDirectory(int directory);

	List<Contact> findContactByUserDirectory(int directory);

}