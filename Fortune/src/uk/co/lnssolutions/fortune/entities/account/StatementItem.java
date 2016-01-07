package uk.co.lnssolutions.fortune.entities.account;

import java.util.Date;
import java.util.Map;

import uk.co.lnssolutions.fortune.enums.ItemClass;

public class StatementItem {
	
	private String refId;
	private Date itemDate;
	private Double amount;
	private Double balance;
	private ItemClass itemclass;
	private Map<String,String> itemClassData;
	private StatementLegacyData legacyData;
	
	//TODO need to create entities.account.StatementLegacyData 
	
}
