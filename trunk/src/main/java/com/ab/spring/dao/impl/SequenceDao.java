package com.ab.spring.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class SequenceDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final String UPDATE_SQL = "UPDATE SEQUENCE SET SEQUENCY_VALUE = SEQUENCY_VALUE + 1 WHERE SEQUENCE_NAME = ?"; 
	private static final String GET_SQL = "SELECT SEQUENCY_VALUE FROM SEQUENCE WHERE SEQUENCE_NAME = ?"; 
	
	public synchronized long getNextVal(String seqName) {
		jdbcTemplate.update(UPDATE_SQL, seqName);
		ResultSetExtractor<Long> rse = rs -> {
			rs.next();
			return rs.getLong("SEQUENCY_VALUE");
		};

		return jdbcTemplate.query(GET_SQL, new Object[] {seqName}, rse);
	}
}