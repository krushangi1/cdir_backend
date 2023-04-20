package com.example.directory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.directory.entity.Contact;
import com.example.directory.entity.Directory;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {

}
