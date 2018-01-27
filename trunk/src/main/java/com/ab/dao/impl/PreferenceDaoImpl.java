package com.ab.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ab.dao.PreferenceDao;
import com.ab.vo.preference.Preference;

@Repository
public class PreferenceDaoImpl implements PreferenceDao{
	
	final static Logger logger = Logger.getLogger(PreferenceDaoImpl.class);
	
	public void savePreference(Preference preference)
	{
		
	}
	
	public Preference getPreference(int preferenceId)
	{
		Preference preference =null;//new Preference();
		return preference;
	}

}
