package com.ab.spring.dao;

import com.ab.vo.preference.Preference;

public interface PreferenceDao {
	
	public void savePreference(Preference preference)
			throws Exception;
	
	public Preference getPreference(int preferenceId);

}
