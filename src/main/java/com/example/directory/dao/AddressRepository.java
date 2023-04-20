package com.example.directory.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.directory.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
}
