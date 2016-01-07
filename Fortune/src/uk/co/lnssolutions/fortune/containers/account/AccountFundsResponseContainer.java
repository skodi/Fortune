package uk.co.lnssolutions.fortune.containers.account;

import uk.co.lnssolutions.fortune.containers.Container;
import uk.co.lnssolutions.fortune.entities.account.AccountFundsResponse;


public class AccountFundsResponseContainer extends Container {

	private AccountFundsResponse result;

	public AccountFundsResponse getResult() {
		return result;
	}

	public void setResult(AccountFundsResponse result) {
		this.result = result;
	}

}
