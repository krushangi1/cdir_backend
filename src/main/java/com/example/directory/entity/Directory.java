package com.example.directory.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "directory")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Directory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "directory_id")
    private int directoryId;

    @Column(name = "full_name")
    private String fullName;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createsAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;


    //mapping to contact,email,address
    @OneToMany(mappedBy = "directory",cascade={CascadeType.ALL},fetch=FetchType.EAGER)
    private List<Email> emails= new ArrayList < > ();
    
    @OneToMany(mappedBy = "directory",cascade={CascadeType.ALL},fetch=FetchType.EAGER)
    private List<Contact> contacts= new ArrayList < > ();
    
    @OneToMany(mappedBy = "directory",cascade={CascadeType.ALL},fetch=FetchType.EAGER)
    private List<Address> addresses= new ArrayList < > ();

    //getter & setters

    public Directory() {
    }
    
    
    public Directory(String fullName, Timestamp createsAt, Timestamp updatedAt, List<Email> emails,
			List<Contact> contacts, List<Address> addresses,int directoryId) {
		super();
		this.fullName = fullName;
		this.createsAt = createsAt;
		this.updatedAt = updatedAt;
		this.emails = emails;
		this.contacts = contacts;
		this.addresses = addresses;
        this.directoryId=directoryId;
	}


	public Directory(Directory tempDirectory) {
		// TODO Auto-generated constructor stub
		super();
		this.fullName = tempDirectory.getFullName();
		this.createsAt = tempDirectory.getCreatesAt();
		this.updatedAt = tempDirectory.getCreatesAt();
	}


	public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public Timestamp getCreatesAt() {

        return createsAt;
    }
    public void setCreatesAt(Timestamp createsAt) {
        this.createsAt = createsAt;
    }
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    public int getDirectoryId() {
        return directoryId;
    }
    public void setDirectoryId(int directoryId) {
        this.directoryId = directoryId;
    }
   

    

    //getter & setter for mapped field
    public List<Email> getEmails(){
    	
        return emails;
    }
    public List<Contact> getContacts(){
        return contacts;
    }
    public List<Address> getAddresses(){
        return addresses;
    }
    public void setEmails(List<Email> emails){
        this.emails=emails;
    }
    public void setContacts(List<Contact> contacts){
        this.contacts=contacts;
    }
    public void setAddresses(List<Address> addresses){
        this.addresses=addresses;
    }

    @Override
    public String toString() {
        return "Directory{" +
                "directoryId=" + directoryId +
                ", fullName='" + fullName + '\'' +
                ", createsAt=" + createsAt +
                ", updatedAt=" + updatedAt +
                ", emails=" + emails +
                ", contacts=" + contacts +
                ", addresses=" + addresses +
                '}';
    }


    //add method for bidirectional relationship
//    public void addEmail(Email tempEmail){
//        if(emails==null){
//            emails=new ArrayList<>();
//        }
//        emails.add(tempEmail);
//        tempEmail.setDirectory(this.directoryId);
//    }
//    public void addAddress(Address tempAddress){
//        if(addresses==null){
//            addresses=new ArrayList<>();
//        }
//        addresses.add(tempAddress);
//        tempAddress.setDirectory(this.directoryId);
//    }
//    public void addContact(Contact tempContact){
//        if(contacts==null){
//            contacts=new ArrayList<>();
//        }
//        contacts.add(tempContact);
//        tempContact.setDirectoryId(this.directoryId);
//    }

}
