package com.quotemaker.quotes.model.response;

import java.util.List;

import com.quotemaker.quotes.model.Quotes;

public class QuotesGeneralResponse {

	private Quotes quote;
	private List<Quotes> quotesList;
	private int errorCode;
	private String errorMsg;
	private String responsePort;
	
	public QuotesGeneralResponse() {
	}
	
	public Quotes getQuote() {
		return quote;
	}
	public void setQuote(Quotes quote) {
		this.quote = quote;
	}
	public List<Quotes> getQuotesList() {
		return quotesList;
	}
	public void setQuotesList(List<Quotes> quotesList) {
		this.quotesList = quotesList;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getResponsePort() {
		return responsePort;
	}
	public void setResponsePort(String responsePort) {
		this.responsePort = responsePort;
	}
	
}
