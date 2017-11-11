package com.ab.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.spring.dao.LocationDao;
import com.ab.spring.service.LocationService;
import com.ab.vo.City;
import com.ab.vo.Country;
import com.ab.vo.State;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationDao locationDao;
	
	@Override
	public List<Country> getAllCountries() {
		return locationDao.getAllCountries();
	}

	@Override
	public Country getCountryBy(long country_id) {
		return locationDao.getCountryBy(country_id);
	}

	@Override
	public List<State> getAllStatesByCountry(Country country) {
		return locationDao.getAllStatesByCountry(country);
	}

	@Override
	public State getStateBy(long state_id) {
		return locationDao.getStateBy(state_id);
	}

	@Override
	public List<City> getAllCitiesByState(State state) {
		return locationDao.getAllCitiesByState(state);
	}

	@Override
	public City getCityBy(long city_id) {
		return locationDao.getCityBy(city_id);
	}

	@Override
	public List<Country> countryAutoComplete(String data) {
		return locationDao.countryAutoComplete(data);
	}
	
	@Override
	public List<State> stateAutoComplete(String data) {
		return locationDao.stateAutoComplete(data);
	}
	
	@Override
	public List<City> cityAutoComplete(String data) {
		return locationDao.cityAutoComplete(data);
	}

}
