package com.quotemaker.quotes.dao;

import org.springframework.stereotype.Repository;

import com.quotemaker.quotes.exceptions.SequenceException;

@Repository
public interface SequenceDao {

	long getNextSequenceId(String key) throws SequenceException;
	
}
