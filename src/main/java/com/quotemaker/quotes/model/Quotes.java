package com.quotemaker.quotes.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "group")
public class Quotes {

	@Id
	private long idGroup;
	
	private String customerName;
	
	private String customerFiscalNumber;
	
	private String quoteDate;

	private List<QuoteDetail> quotes;
	
	private double totalPriceWithoutIVA;
	
	private double iva;
	
	private double total;
	
	public Quotes() {
	}
	
	public long getIdGroup() {
		return idGroup;
	}
	
	public void setIdGroup(long idGroup) {
		this.idGroup = idGroup;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerFiscalNumber() {
		return customerFiscalNumber;
	}

	public void setCustomerFiscalNumber(String customerFiscalNumber) {
		this.customerFiscalNumber = customerFiscalNumber;
	}

	public String getQuoteDate() {
		return quoteDate;
	}

	public void setQuoteDate(String quoteDate) {
		this.quoteDate = quoteDate;
	}

	public List<QuoteDetail> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<QuoteDetail> quotes) {
		this.quotes = quotes;
	}

	public double getTotalPriceWithoutIVA() {
		return totalPriceWithoutIVA;
	}

	public void setTotalPriceWithoutIVA(double totalPriceWithoutIVA) {
		this.totalPriceWithoutIVA = totalPriceWithoutIVA;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
}
