package com.example.directory;

import java.sql.Timestamp;
import java.util.List;

import com.example.directory.entity.Address;
import com.example.directory.entity.Contact;
import com.example.directory.entity.Email;

public class DirectoryDto {

	private int id;
	private String fullName;
	private Timestamp createsAt;
	private Timestamp updatedAt;
    private List<Email> emails;
    private List<Contact> contacts;
    private List<Address> addresses;
	public int getId() {
		return id;
	}
	public void setId(int directoryId) {
		this.id = directoryId;
	}
	public List<Email> getEmails() {
		return emails;
	}
	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public String getFullName() {
		return fullName;
	}

	public Timestamp getCreatesAt() {
		return createsAt;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	public DirectoryDto(int directoryId, List<Email> emails,Timestamp updatedAt,Timestamp createsAt, List<Contact> contacts, List<Address> addresses) {
		super();
		this.id = directoryId;
		this.emails = emails;
		this.contacts = contacts;
		this.addresses = addresses;
		this.createsAt=createsAt;
		this.updatedAt=updatedAt;
	}
	public DirectoryDto() {
		super();
	}

	@Override
	public String toString() {
		return "DirectoryDto{" +
				"directoryId=" + id +
				", fullName='" + fullName + '\'' +
				", createsAt=" + createsAt +
				", updatedAt=" + updatedAt +
				", emails=" + emails +
				", contacts=" + contacts +
				", addresses=" + addresses +
				'}';
	}
}
