package com.ab.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ab.application.config.SpringApp;
import com.ab.service.LocationService;
import com.ab.vo.City;
import com.ab.vo.Country;
import com.ab.vo.State;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringApp.class)
@WebAppConfiguration
public class LocationServiceTest {

	@Autowired
	private LocationService locationService;
	
	@Test
	public void testGetAllCountries() {
		List<Country> allCountries = locationService.getAllCountries();
		Assert.assertEquals(246, allCountries.size());
	}
	
	@Test
	public void testGetCountryById() {
		Country country = locationService.getCountryBy(101l);
		Assert.assertEquals(101l, country.getId());
		Assert.assertEquals("India", country.getName());
	}

	@Test
	public void testGetStateByCountry() {
		List<State> states = locationService.getAllStatesByCountry(new Country(101, "", "", 91));
		Assert.assertEquals(41, states.size());
	}

	@Test
	public void testGetSateById() {
		State state = locationService.getStateBy(22l);
		Assert.assertEquals(22l, state.getId());
		Assert.assertEquals("Maharashtra", state.getName());
	}

	@Test
	public void testGetCitiesByState() {
		List<City> cities = locationService.getAllCitiesByState(new State(22, "", null));
		Assert.assertEquals(410, cities.size());
	}

	@Test
	public void testGetCityById() {
		City city = locationService.getCityBy(2707l);
		Assert.assertEquals(2707l, city.getId());
		Assert.assertEquals("Mumbai", city.getName());
	}
}
