package com.ab.spring.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ab.spring.dao.AddressDao;
import com.ab.vo.Address;

@Repository
public class AddressDaoImpl implements AddressDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SequenceDao sequenceDao;
	
	@Override
	public Address saveAddress(Address address) {
		String sql = "INSERT INTO ADDRESS (ADDRESS_ID, ADDRESS_LINE_1, ADDRESS_LINE_2, LANDMARK, CITY, STATE, COUNTRY, ZIPCODE, PHONE, SECONDARY_PHONE, CREATED_BY, CREATED_TIME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, SYSDATE())";
		Long addressId = sequenceDao.getNextVal("ADDRESS_SEQ");
		jdbcTemplate.update(sql, addressId, address.getAddressLine1(), address.getAddressLine2(), address.getLandmark(), address.getCity(), address.getState(), address.getCountry(), address.getZipcode(), address.getPhonePrimary(), address.getPhoneSecondary());
		address.setAddressId(addressId);
		return address;
	}

	@Override
	public void updateAddress(Address address) {
		String sql = "UPDATE ADDRESS SET ADDRESS_LINE_1=?, ADDRESS_LINE_2=?, LANDMARK=?, CITY=?, STATE=?, COUNTRY=?, ZIPCODE=?, PHONE=?, SECONDARY_PHONE=?, MODIFIED_BY=?, MODIFIED_TIME=SYSDATE()) WHERE ADDRESS_ID=?";
		jdbcTemplate.update(sql, address.getAddressLine1(), address.getAddressLine2(), address.getLandmark(), address.getCity(), address.getState(), address.getCountry(), address.getZipcode(), address.getPhonePrimary(), address.getPhoneSecondary(), 0, address.getAddressId());
	}

	@Override
	public Address getAddress(Long addressId) {
		String sql = "SELECT ADDRESS_ID, ADDRESS_LINE_1, ADDRESS_LINE_2, LANDMARK, CITY, STATE, COUNTRY, ZIPCODE, PHONE, SECONDARY_PHONE, CREATED_BY, CREATED_TIME, MODIFIED_BY, MODIFIED_TIME FROM ADDRESS WHERE ADDRESS_ID=?";
		return jdbcTemplate.query(sql, new Object[] {addressId}, (ResultSetExtractor<Address>) rs -> {
				rs.next();
				Address address = new Address();
				address.setAddressId(addressId);
				address.setAddressLine1(rs.getString("ADDRESS_LINE_1"));
				address.setAddressLine2(rs.getString("ADDRESS_LINE_2"));
				address.setLandmark(rs.getString("LANDMARK"));
				address.setCity(rs.getString("CITY"));
				address.setState(rs.getString("STATE"));
				address.setCountry(rs.getString("COUNTRY"));
				address.setZipcode(rs.getString("ZIPCODE"));
				address.setPhonePrimary(rs.getLong("PHONE"));
				address.setPhoneSecondary(rs.getLong("SECONDARY_PHONE"));
				return address;
			});
	}

	@Override
	public void removeAddress(Long addressId) {
		String sql = "DELETE FROM ADDRESS WHERE ADDRESS_ID=?";
		jdbcTemplate.update(sql, addressId);
	}
}
