package com.ab.spring.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ab.spring.dao.LocationDao;
import com.ab.vo.City;
import com.ab.vo.Country;
import com.ab.vo.State;

@Repository
public class LocationDaoImpl implements LocationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Country> getAllCountries() {
		String sql = "SELECT ID, COUNTRY_CODE, NAME, PHONE_CODE FROM COUNTRIES";
		return jdbcTemplate.query(sql, (ResultSet rs, int arg1) -> new Country(rs.getLong("ID"), rs.getString("NAME"), rs.getString("COUNTRY_CODE"), rs.getInt("PHONE_CODE")));
	}

	@Override
	public Country getCountryBy(long country_id) {
		String sql = "SELECT ID, COUNTRY_CODE, NAME, PHONE_CODE FROM COUNTRIES WHERE ID=?";
		return jdbcTemplate.query(sql, new Object[] { country_id }, (rs -> {
														rs.next();
														return new Country(rs.getLong("ID"), rs.getString("NAME"), rs.getString("COUNTRY_CODE"), rs.getInt("PHONE_CODE"));
														}));
	}

	@Override
	public List<State> getAllStatesByCountry(Country country) {
		String sql = "SELECT ID, NAME FROM STATES WHERE COUNTRY_ID = ?";
		return jdbcTemplate.query(sql, new Object[] {country.getId()},(ResultSet rs, int arg1) -> new State(rs.getLong("ID"), rs.getString("NAME"), country));
	}

	@Override
	public State getStateBy(long state_id) {
		String sql = "SELECT ID, NAME, COUNTRY_ID FROM STATES WHERE ID = ?";
		return jdbcTemplate.query(sql, new Object[] { state_id }, (rs -> {
														rs.next();
														return new State(rs.getLong("ID"), rs.getString("NAME"), getCountryBy(rs.getLong("COUNTRY_ID")));
														}));
	}

	@Override
	public List<City> getAllCitiesByState(State state) {
		String sql = "SELECT ID, NAME FROM CITIES WHERE STATE_ID = ?";
		return jdbcTemplate.query(sql, new Object[] {state.getId()},(ResultSet rs, int arg1) -> new City(rs.getLong("ID"), rs.getString("NAME"), state));
	}

	@Override
	public City getCityBy(long city_id) {
		String sql = "SELECT ID, NAME, STATE_ID FROM CITIES WHERE ID = ?";
		return jdbcTemplate.query(sql, new Object[] { city_id }, (rs -> {
														rs.next();
														return new City(rs.getLong("ID"), rs.getString("NAME"), getStateBy(rs.getLong("STATE_ID")));
														}));
	}

	@Override
	public List<Country> countryAutoComplete(String data) {
		String sql = "SELECT ID, COUNTRY_CODE, NAME, PHONE_CODE FROM COUNTRIES where lower(name) like lower(?) ";
		return jdbcTemplate.query
				(sql,new Object[] { data+"%" }, (ResultSet rs, int arg1) -> new Country(rs.getLong("ID"), rs.getString("NAME"), rs.getString("COUNTRY_CODE"), rs.getInt("PHONE_CODE")));
	
	}
	
	@Override
	public List<State> stateAutoComplete(String data) {
		String sql = "SELECT ID, NAME  FROM STATES where lower(name) like lower(?) ";
		return jdbcTemplate.query
				(sql,new Object[] { data+"%" }, (ResultSet rs, int arg1) -> new State(rs.getLong("ID"), rs.getString("NAME")));
	
	}
	
	@Override
	public List<City> cityAutoComplete(String data) {
		String sql = "SELECT ID, NAME FROM CITIES where lower(name) like lower(?) ";
		return jdbcTemplate.query
				(sql,new Object[] { data+"%" }, (ResultSet rs, int arg1) -> new City(rs.getLong("ID"), rs.getString("NAME")));
	
	}
}
