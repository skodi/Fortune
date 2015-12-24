package uk.co.lnssolutions.fortune.containers;

import java.util.List;

import uk.co.lnssolutions.fortune.entities.MarketCatalogue;

public class ListMarketCatalogueContainer extends Container {

	private List<MarketCatalogue> result;

	public List<MarketCatalogue> getResult() {
		return result;
	}

	public void setResult(List<MarketCatalogue> result) {
		this.result = result;
	}

}
