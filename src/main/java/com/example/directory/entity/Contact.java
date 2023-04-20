package com.example.directory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private int contactId;

//    @ManyToOne
    @Column(name = "directory_id")
    private int directory;

    @Column(name = "no")
    private String no;

    @Column(name = "type")
    private String type;

    public Contact() {
    }

    public Contact(int directory_id, String no, String type) {
        this.directory = directory_id;
        this.no = no;
        this.type = type;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public int getDirectoryId() {
        return directory;
    }

    public void setDirectoryId(int directory_idId) {
        this.directory = directory_idId;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", directory_id=" + directory +
                ", no=" + no +
                ", type='" + type + '\'' +
                '}';
    }
}
