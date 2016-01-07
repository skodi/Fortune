package uk.co.lnssolutions.fortune.entities.account;

import java.util.Date;

import uk.co.lnssolutions.fortune.enums.MarketType;
import uk.co.lnssolutions.fortune.enums.WinLose;

public class StatementLegacyData {

	private Double 	avgPrice;
	private Double 	betSize;
	private String 	betType;
	private String 	betCategoryType;
	private String 	commissionRate;
	private Long   	eventId;
	private Long	eventTypeId;
	private String	fullMarketName;
	private Double  grossBetAmount;
	private String	marketName;
	private MarketType marketType;
	private Date	placedDate;
	private Long	selectionId;
	private String	selectionName;
	private Date	startDate;
	private String	transactionType;
	private Long	transactionId;
	private WinLose winLose;
	
}
