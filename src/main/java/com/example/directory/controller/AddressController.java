package com.example.directory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.directory.ServiceImpl.AddressServiceImpl;
import com.example.directory.entity.Address;
import com.example.directory.entity.Contact;
import com.example.directory.entity.Email;
import com.example.directory.service.AddressService;

@RestController
@RequestMapping("/add")
public class AddressController {

    private AddressService addressServiceImpl;
    
    
    public AddressController() {
	}

    @Autowired
    public AddressController(AddressService addressServiceImpl){
        this.addressServiceImpl=addressServiceImpl;
    }
    //----------------------------find all addresses
    @GetMapping("/all")
    public List<Address> getAddresses(){
        return addressServiceImpl.getAddresses();
    }
    
    //----------------------------find address by id
    @GetMapping("/all/{addressId}")
    public Address findById(@PathVariable("addressId") int addressId){
        return addressServiceImpl.findById(addressId);
    }

    //----------------------------delete address by id
    @DeleteMapping("/all/{addressId}")
    public void deleteById(@PathVariable("addressId") int addressId){
        addressServiceImpl.deleteById(addressId);
    }
    
    //----------------------------create address
    @PostMapping("/all")
    public Address saveAddress(@RequestBody Address tempAddress){
    	tempAddress.setAddressId(0);
         addressServiceImpl.saveAddress(tempAddress);
         return addressServiceImpl.saveAddress(tempAddress);
    }
    
    //----------------------------update address
    @PutMapping("all")
    public Address updateContact(@RequestBody Address tempAddress){
    	Address  theAddress= addressServiceImpl.saveAddress(tempAddress);
    	try{
        	return theAddress;
        }
        catch(Exception e){
        	return null;
        }
    }
    
  //-----------------------------------find by user id
    @GetMapping("all/user/{dirId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Address> findByUser(@PathVariable("dirId") int directory){
    	return addressServiceImpl.findAddressByUserDirectory(directory);
    }
}
