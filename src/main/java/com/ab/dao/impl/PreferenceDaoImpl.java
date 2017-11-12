package com.ab.dao.impl;

import org.springframework.stereotype.Repository;

import com.ab.dao.PreferenceDao;
import com.ab.vo.preference.Preference;

@Repository
public class PreferenceDaoImpl implements PreferenceDao{
	
	public void savePreference(Preference preference)
	{
		
	}
	
	public Preference getPreference(int preferenceId)
	{
		Preference preference =null;//new Preference();
		return preference;
	}

}
