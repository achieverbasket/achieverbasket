package com.ab.spring.dao;

import com.ab.vo.Address;

public interface AddressDao {
	Address saveAddress(Address address);
	
	void updateAddress(Address address);
	
	Address getAddress(Long addressId);
	
	void removeAddress(Long addressId);
}
