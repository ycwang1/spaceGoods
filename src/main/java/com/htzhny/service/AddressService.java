package com.htzhny.service;

import java.util.List;

import com.htzhny.entity.Address;

public interface AddressService {
	public List<Address> findAddressByUserId(Integer user_id);
	public Integer updateAddressbyId(Address address);
	public  Integer addAddress(Address address);
	public Address findAddressById(Integer id);
	} 
