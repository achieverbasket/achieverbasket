package com.ab.dao;

import java.util.List;

import com.ab.vo.City;
import com.ab.vo.Country;
import com.ab.vo.State;

public interface LocationDao {
	
	List<Country> getAllCountries();
	
	Country getCountryBy(long country_id);

	List<State> getAllStatesByCountry(Country country);
	
	State getStateBy(long state_id);

	List<City> getAllCitiesByState(State state);
	
	City getCityBy(long city_id);
	
	List<Country> countryAutoComplete(String data);
	
	List<State> stateAutoComplete(String data);
	
	List<City> cityAutoComplete(String data);
}
