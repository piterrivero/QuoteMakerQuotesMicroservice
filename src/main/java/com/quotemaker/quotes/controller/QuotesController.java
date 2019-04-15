package com.quotemaker.quotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quotemaker.quotes.ErrorCodes;
import com.quotemaker.quotes.dao.QuotesRepository;
import com.quotemaker.quotes.dao.SequenceDao;
import com.quotemaker.quotes.model.Quotes;
import com.quotemaker.quotes.model.response.QuotesGeneralResponse;

@CrossOrigin
@RestController
public class QuotesController extends ErrorCodes {

	private static final String QUOTES_SEQ_KEY = "quote";
	
	@Autowired
	Environment environment;
	@Autowired
	QuotesRepository quotesRepository;
	@Autowired
	SequenceDao sequenceDao;
	
	@GetMapping("/quotes/test")
	public String test(){
	    String resp = "The Quotes Service is works on the port: " + environment.getProperty("local.server.port");
	    return resp;
	}
	
	@RequestMapping(value = "/quotes/list")
	public QuotesGeneralResponse list(@RequestParam String order){
		List<Quotes> quotesList = null;
		
		if (order != null && !order.equals("") && order.toUpperCase().equals("ASC")) {
			quotesList = quotesRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
		}else if (order != null && !order.equals("") && order.toUpperCase().equals("DESC")) {
			quotesList = quotesRepository.findAll(new Sort(Sort.Direction.DESC, "name"));
		}
		
		QuotesGeneralResponse groupGeneralResponse = new QuotesGeneralResponse();
		groupGeneralResponse.setErrorCode(0);
		groupGeneralResponse.setErrorMsg("OK");
		groupGeneralResponse.setQuotesList(quotesList);
		groupGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		
		return groupGeneralResponse;
	}
	
	@RequestMapping(value = "/quotes/find")
	public QuotesGeneralResponse findById(@RequestParam String id){
		
		QuotesGeneralResponse quoteGeneralResponse = new QuotesGeneralResponse();
		
		Quotes quote = quotesRepository.findById(Long.parseLong(id));
		
		if (quote == null) {
			quoteGeneralResponse.setErrorCode(RECORD_NOT_FOUND);
			quoteGeneralResponse.setErrorMsg("Error: Quote not found");
			quoteGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		} else {
			quoteGeneralResponse.setErrorCode(0);
			quoteGeneralResponse.setErrorMsg("OK");
			quoteGeneralResponse.setQuote(quote);
			quoteGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		}
		
	    return quoteGeneralResponse;
	}
	
	@RequestMapping(value = "/quotes/add", method = RequestMethod.POST)
	public QuotesGeneralResponse add(@RequestBody(required=true) Quotes quote){
		quote.setIdGroup(sequenceDao.getNextSequenceId(QUOTES_SEQ_KEY));
		quotesRepository.save(quote);
		
		QuotesGeneralResponse quoteGeneralResponse = new QuotesGeneralResponse();
		quoteGeneralResponse.setErrorCode(0);
		quoteGeneralResponse.setErrorMsg("OK");
		quoteGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		
		return quoteGeneralResponse;
	}
	
	@RequestMapping(value = "/quotes/edit", method = RequestMethod.PATCH)
	public QuotesGeneralResponse edit(@RequestBody(required=true) Quotes quote){
		
		QuotesGeneralResponse quoteGeneralResponse = new QuotesGeneralResponse();
		
		if (quote.getIdGroup() == 0) {
			quoteGeneralResponse.setErrorCode(REQUIRED_FIELD);
			quoteGeneralResponse.setErrorMsg("Error: the field quoteId is required");
			quoteGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		}else {
			QuotesGeneralResponse ggrTemp = findById(String.valueOf(quote.getIdGroup()));
			if (ggrTemp.getErrorCode() == RECORD_NOT_FOUND){
				quoteGeneralResponse.setErrorCode(RECORD_NOT_FOUND);
				quoteGeneralResponse.setErrorMsg("Error: Quote not found");
				quoteGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
			}else {
				quoteGeneralResponse.setErrorCode(0);
				quoteGeneralResponse.setErrorMsg("OK");
				quoteGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
				quotesRepository.save(quote);
			}
		}
		
		return quoteGeneralResponse;
	}
	
	@RequestMapping(value = "/quotes/delete")
	public QuotesGeneralResponse delete(@RequestParam String id){
		
		QuotesGeneralResponse quoteGeneralResponse = new QuotesGeneralResponse();
		
		Quotes quote = quotesRepository.findById(Long.parseLong(id));
		
		if (quote == null){
			quoteGeneralResponse.setErrorCode(RECORD_NOT_FOUND);
			quoteGeneralResponse.setErrorMsg("Error: Quote not found");
			quoteGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		}else {
			quotesRepository.delete(quote);
			quoteGeneralResponse.setErrorCode(0);
			quoteGeneralResponse.setErrorMsg("OK");
			quoteGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		}
		
		return quoteGeneralResponse;
	}
}
