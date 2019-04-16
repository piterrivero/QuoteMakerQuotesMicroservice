package com.quotemaker.quotes.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface SequenceDao {

	long getNextSequenceId(String key);
	
}
