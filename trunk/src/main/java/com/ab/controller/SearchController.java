package com.ab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ab.service.LocationService;
import com.ab.vo.City;
import com.ab.vo.Country;
import com.ab.vo.State;



@Controller
public class SearchController {
	@Autowired
	LocationService locationServiceImpl;
	
	@ResponseBody
	 @RequestMapping(value= "/country" , method = RequestMethod.GET )
		public List<Country> countryAutoComplete(@RequestParam(value="query",required=false) String query){
		 List<Country> result = null;
		try {
				result = locationServiceImpl.countryAutoComplete(query);
		} catch (Exception ex) {
			
		}
		
		return result;
	} 
	
	@ResponseBody
	 @RequestMapping(value= "/state" , method = RequestMethod.GET )
		public List<State> stateAutoComplete(@RequestParam(value="query",required=false) String query){
		 List<State> result = null;
		try {
				result = locationServiceImpl.stateAutoComplete(query);
		} catch (Exception ex) {
			
		}
		
		return result;
	} 
	
	@ResponseBody
	 @RequestMapping(value= "/city" , method = RequestMethod.GET )
		public List<City> cityAutoComplete(@RequestParam(value="query",required=false) String query){
		 List<City> result = null;
		try {
				result = locationServiceImpl.cityAutoComplete(query);
		} catch (Exception ex) {
			
		}
		
		return result;
	} 
}
