package com.quotemaker.quotes.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.quotemaker.quotes.model.SequenceId;

@Repository
public interface SequenceRepository extends MongoRepository<SequenceId, Long> {

		
}
