package com.htzhny.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htzhny.dao.AddressDao;
import com.htzhny.entity.Address;
import com.htzhny.service.AddressService;



@Service
@Transactional
public class AddressServiceImpl implements AddressService{
	@Resource
	AddressDao addressDao;
	@Transactional(readOnly=true)
	public List<Address> findAddressByUserId(Integer user_id){
		return addressDao.findAddressByUserId(user_id);
	}
	public Integer updateAddressbyId(Address address){
		return addressDao.updateAddressbyId(address);
	}
	public Integer addAddress(Address address){
		return addressDao.addAddress(address);
	}
	@Override
	public Address findAddressById(Integer id) {
		return addressDao.findAddressById(id);
	}

}
