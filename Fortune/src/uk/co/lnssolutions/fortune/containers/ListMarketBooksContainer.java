package uk.co.lnssolutions.fortune.containers;

import java.util.List;

import uk.co.lnssolutions.fortune.entities.MarketBook;

public class ListMarketBooksContainer extends Container{
	
	private List<MarketBook> result;
		
	public List<MarketBook> getResult() {
		return result;
	}
	public void setResult(List<MarketBook> result) {
		this.result = result;
	}
}
