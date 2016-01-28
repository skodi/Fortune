package uk.co.lnssolutions.fortune.containers.account;

import java.util.List;

import uk.co.lnssolutions.fortune.containers.Container;
import uk.co.lnssolutions.fortune.entities.account.AccountFundsResponse;
import uk.co.lnssolutions.fortune.entities.account.AccountStatementReport;
import uk.co.lnssolutions.fortune.entities.account.StatementItem;

public class AccountStatementContainer extends Container {

	private AccountStatementReport result;

	public AccountStatementReport getResult() {
		return result;
	}

	public void setResult(AccountStatementReport result) {
		this.result = result;
	}
	
}
