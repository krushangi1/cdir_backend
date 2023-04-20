package com.example.directory.ServiceImpl;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.directory.dao.AddressRepository;
import com.example.directory.entity.Address;
import com.example.directory.entity.Contact;
import com.example.directory.entity.Email;
import com.example.directory.service.AddressService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Service
public class AddressServiceImpl implements AddressService{
	
	private AddressRepository addressRepository;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public AddressServiceImpl() {
		super();
	}
	
	@Autowired
	public AddressServiceImpl(AddressRepository addressRepository) {
		this.addressRepository=addressRepository;
	}

	//----------------------------find all addresses
	@Override
	public List<Address> getAddresses(){
		return addressRepository.findAll();
	}
	
	//----------------------------find address by id
	@Override
	public Address findById(int addressId) {
		 java.util.Optional<Address> tempAddress=addressRepository.findById(addressId);
		 Address theAddress=null;
		 
		 if(tempAddress.isPresent()) {
			 theAddress=tempAddress.get();
		 }
		 else {
	            throw new RuntimeException("Did not find the address with id - " + addressId);
	        }
		 
		 return theAddress;
	 }
	 
	//----------------------------delete address by id
	@Override
	public void deleteById(int addressId) {
		 java.util.Optional<Address> tempAddress=addressRepository.findById(addressId);
		 Address theAddress=null;
		 
		 if(tempAddress.isPresent()) {
			 theAddress=tempAddress.get();
		 }
		 else {
	            throw new RuntimeException("Did not find the address with id - " + addressId);
	        }
		 addressRepository.deleteById(addressId);
	 }
	 
	//----------------------------save or delete address 
	@Override
	public Address saveAddress(Address addressList) {
		 Address theAddress=addressRepository.save(addressList);
		 return theAddress;
	 }

	//-----------------------------------------------custom method implementation
    @Override
    public List<Address> findAddressByUserDirectory(int directory){
    	String hql = "FROM Address as  e WHERE e.directory=:dirId";
        TypedQuery<Address> query = entityManager.createQuery(hql, Address.class);
        query.setParameter("dirId", directory);
        List<Address> theAddress =query.getResultList();
        return theAddress;
    }
}
