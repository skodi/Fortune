package uk.co.lnssolutions.fortune.entities;



import java.util.Date;

import uk.co.lnssolutions.fortune.enums.InstructionReportErrorCode;
import uk.co.lnssolutions.fortune.enums.InstructionReportStatus;

public class PlaceInstructionReport {
	private InstructionReportStatus status;
	private InstructionReportErrorCode errorCode;
	private PlaceInstruction instructionl;
	private String betId;
	private Date placedDate;
	private double averagePriceMatched;
	private double sizeMatched;

	public InstructionReportStatus getStatus() {
		return status;
	}

	public void setStatus(InstructionReportStatus status) {
		this.status = status;
	}

	public InstructionReportErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(InstructionReportErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public PlaceInstruction getInstructionl() {
		return instructionl;
	}

	public void setInstructionl(PlaceInstruction instructionl) {
		this.instructionl = instructionl;
	}

	public String getBetId() {
		return betId;
	}

	public void setBetId(String betId) {
		this.betId = betId;
	}

	public Date getPlacedDate() {
		return placedDate;
	}

	public void setPlacedDate(Date placedDate) {
		this.placedDate = placedDate;
	}

	public double getAveragePriceMatched() {
		return averagePriceMatched;
	}

	public void setAveragePriceMatched(double averagePriceMatched) {
		this.averagePriceMatched = averagePriceMatched;
	}

	public double getSizeMatched() {
		return sizeMatched;
	}

	public void setSizeMatched(double sizeMatched) {
		this.sizeMatched = sizeMatched;
	}

}
