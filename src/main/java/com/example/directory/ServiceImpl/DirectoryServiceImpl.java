package com.example.directory.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.directory.DirectoryDto;
import com.example.directory.dao.AddressRepository;
import com.example.directory.dao.ContactRepository;
import com.example.directory.dao.DirectoryRepository;
import com.example.directory.dao.EmailRepository;
import com.example.directory.entity.Address;
import com.example.directory.entity.Contact;
import com.example.directory.entity.Directory;
import com.example.directory.entity.Email;
import com.example.directory.service.AddressService;
import com.example.directory.service.ContactService;
import com.example.directory.service.DirectoryService;
import com.example.directory.service.EmailService;

@Service
public   class DirectoryServiceImpl implements DirectoryService {

    private DirectoryRepository directoryRepository;
    private EmailService emailService;
    private ContactService contactService;
    private AddressService addressService;
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private AddressRepository addressRepository;
//    private EmailServiceImpl emailService = new EmailServiceImpl();
    
    public DirectoryServiceImpl() {
		super();
	}
    
    
    @Autowired
    public DirectoryServiceImpl(DirectoryRepository directoryRepository, EmailService emailService,
    		ContactService contactService,AddressService addressService){
        this.directoryRepository=directoryRepository;
        this.emailService=emailService;
        this.addressService=addressService;
        this.contactService=contactService;
        
    }

    //----------------------------find all directories
    @Override
    public List<Directory> getDirectories(){
        return directoryRepository.findAll();
    }
    
    
	
	//----------------------------delete email by id
    @Override
    public void deleteById(int directoryId){
    	Optional<Directory> tempDirectory=directoryRepository.findById(directoryId);
		Directory theDirectory = null;
		if( tempDirectory.isPresent() ) {
			theDirectory=tempDirectory.get();
			
		}
		else {
            throw new RuntimeException("Did not find the directory with id - " + directoryId);
        }
        directoryRepository.deleteById(directoryId);
        
    }

    //----------------------------save directory
    @Override
    public Directory saveDirectory(Directory tempDirectory) {
    	
    	Directory directory=new Directory(tempDirectory);

    	directory=directoryRepository.save(directory);
    	
    	int a=directory.getDirectoryId();
 
    	for(Email email : tempDirectory.getEmails()) {
    		email.setDirectory(a);
    		emailRepository.save(email);
    	}
    	for(Contact contact : tempDirectory.getContacts()) {
    		contact.setDirectoryId(a);
    		contactRepository.save(contact);
    	}
    	for(Address address : tempDirectory.getAddresses()) {
    		address.setDirectory(a);
    		addressRepository.save(address);
    	}
         return directory;
    }
    
    //---------------------------------update
    @Override
    public Directory updateUser(Integer id, Directory userDetails) {
    
    	Optional<Directory> tempDirectory=directoryRepository.findById(id);
  		Directory theDirectory = null;
  		
  		if( tempDirectory.isPresent() ) {
  			theDirectory=tempDirectory.get();
  			theDirectory.setFullName(userDetails.getFullName());
  			theDirectory.setEmails(userDetails.getEmails());
  			theDirectory.setContacts(userDetails.getContacts());
  			theDirectory.setAddresses(userDetails.getAddresses());
  			System.out.println("--------------in impl save update user");
  			System.out.println(theDirectory);
  			return directoryRepository.save(theDirectory);
 
  		}
  		else {
              throw new RuntimeException("Did not find the directory with id - " + id);
          }

          
    }
    
    
    
  //----------------------------find directory by id
  	@Override
      public DirectoryDto findById(int directoryId){
  		
  		Optional<Directory> tempDirectory=directoryRepository.findById(directoryId);
  		 DirectoryDto directoryDto = new DirectoryDto();
  		
  		List<Email> emails=new ArrayList<Email>();
  		List<Contact> contacts=new ArrayList<Contact>();
  		List<Address> addresses=new ArrayList<Address>();		
  		emails=emailService.findEmailByUserDirectory(directoryId);
  		contacts=contactService.findContactByUserDirectory(directoryId);
  		addresses=addressService.findAddressByUserDirectory(directoryId);

  		if( tempDirectory.isPresent() ) {
  			directoryDto.setId(tempDirectory.get().getDirectoryId());
			  directoryDto.setFullName(tempDirectory.get().getFullName());
			  directoryDto.setCreatesAt(tempDirectory.get().getCreatesAt());
            directoryDto.setAddresses(addresses);
            directoryDto.setContacts(contacts);
            directoryDto.setEmails(emails);
  		}
  		else {
              throw new RuntimeException("Did not find the directory with id - " + directoryId);
          }
          return directoryDto;
      }
  	
  	//-----------------------------------for search
  	@Override
  	public List<Directory> search(String instance){
  		List<Directory> personList = directoryRepository.findByContaining(instance);    	
    	
  		System.out.println("---------------------search Dimpl-------------");
  		System.out.println(directoryRepository.findByContaining(instance));
  		
    	if (!personList.isEmpty()) {
	   		return personList;
	       }
    	else {
	           throw new IllegalArgumentException("Persons with given email doesn't exist");
	       }
  	}
}
