package com.quotemaker.quotes.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.quotemaker.quotes.model.Quotes;

@Repository
public interface QuotesRepository extends MongoRepository<Quotes, Long> {
	
	List<Quotes> findAll(Sort sortByNameAtAsc);
	
	Quotes findById(long id);

		
}
