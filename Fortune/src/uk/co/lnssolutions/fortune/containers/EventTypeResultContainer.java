package uk.co.lnssolutions.fortune.containers;

import java.util.List;

import uk.co.lnssolutions.fortune.entities.EventTypeResult;

public class EventTypeResultContainer extends Container{
	
	private List<EventTypeResult> result;
		
	public List<EventTypeResult> getResult() {
		return result;
	}
	public void setResult(List<EventTypeResult> result) {
		this.result = result;
	}
}
