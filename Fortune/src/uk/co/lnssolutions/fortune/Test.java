package uk.co.lnssolutions.fortune;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import uk.co.lnssolutions.fortune.api.ApiNgJsonRpcOperations;
import uk.co.lnssolutions.fortune.api.ApiNgOperations;
import uk.co.lnssolutions.fortune.constants.Constants;
import uk.co.lnssolutions.fortune.entities.EventTypeResult;
import uk.co.lnssolutions.fortune.entities.MarketFilter;
import uk.co.lnssolutions.fortune.entities.TimeRange;
import uk.co.lnssolutions.fortune.entities.account.AccountFundsResponse;
import uk.co.lnssolutions.fortune.entities.account.AccountStatementReport;
import uk.co.lnssolutions.fortune.enums.IncludeItem;
import uk.co.lnssolutions.fortune.enums.Wallet;
import uk.co.lnssolutions.fortune.exceptions.APINGException;
import uk.co.lnssolutions.fortune.json.utils.ISO8601DateTypeAdapter;
import uk.co.lnssolutions.fortune.json.utils.JsonrpcRequest;
import uk.co.lnssolutions.fortune.operations.Authentication;
import uk.co.lnssolutions.fortune.constants.Fortune;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Test {

	private static String appKey;
    private ApiNgOperations jsonOperations = ApiNgJsonRpcOperations.getInstance();
    
	public static void main(String[] args) {
		
		Test t = new Test();
		t.run();
		
		/** So the test plan then needs to be
		 *  Login - get Session token
		 *  use session token to get something from the account API
		 *  parse response
		 *  persist it somewhere. 
		 * 
		 */
		
	}

	public void run(){
	   
	   //Grab the properties
	   Fortune fortune = new Fortune();
		
	   // System.out.println(getHttpResponseWithSSL("http://www.here.com"));	
       Authentication a = new Authentication();
       
       // Login
       String token =  a.login(fortune.getProp().getProperty("USERNAME"),fortune.getProp().getProperty("PASSWORD"));
	   System.out.println("token: ["+token+"]");
	   appKey = fortune.getProp().getProperty("APPKEY");
	   System.out.println("appkey:["+appKey+"]");
	   
	   // Make call to Betfair to get events
	   try {
		   
           MarketFilter marketFilter;
           marketFilter = new MarketFilter();
           Set<String> eventTypeIds = new HashSet<String>();

           System.out.println("1.(listEventTypes) Get all Event Types...\n");
           List<EventTypeResult> r = jsonOperations.listEventTypes(marketFilter, appKey, token);
           for (EventTypeResult eventTypeResult : r) {
             //  if(eventTypeResult.getEventType().getName().equals("Horse Racing")){
                   System.out.println("3. EventTypeId for "+eventTypeResult.getEventType().getName() + " " + eventTypeResult.getEventType().getId()+"\n");
                   eventTypeIds.add(eventTypeResult.getEventType().getId().toString());
             //  }
           }
		   
           // OK, let's get a P&L
           System.out.println("Working on account");
           AccountFundsResponse funds  = jsonOperations.GetAccountFunds("wallet", appKey, token);
           if (funds != null)
           {
        	   System.out.println("Available funds (UK) :"+funds.getAvailableToBetBalance());        	   
           }
           
           // Then let's try for a statement request. Complex response
           System.out.println("Trying for statement");
           
           // Setup fields for search
           String locale = Locale.getDefault().toString();
          
           TimeRange dateRange = new TimeRange();
           Calendar cal = Calendar.getInstance();
           cal.setTime(new Date());
           dateRange.setTo(cal.getTime());
           cal.add(Calendar.DAY_OF_YEAR,-5);
           dateRange.setFrom(cal.getTime());
           System.out.println("Date range "+dateRange.toString());
           // In reality, date range will be max(statement date in database) -> now. 
           
           AccountStatementReport report = jsonOperations.GetAccountStatement(
        	        locale, 
        	        0, 
        	        10, 
        	        dateRange, 
        	        IncludeItem.ALL, 
        	        Wallet.UK, 
        	        appKey, 
        	        token);
           
	   }catch(APINGException e)
	   {
		   System.out.println("Error "+e.toString());
	   }
	   
	   //Logout
	   token = Authentication.logout();
	   System.out.println(token);		   

	}
	
	public int getMarkets(String token){
	
		String requestString;
		//Handling the JSON-RPC request
		JsonrpcRequest request = new JsonrpcRequest();
		request.setId("1");
		request.setMethod(Constants.SPORTS_APING_V1_0); // ApiNGDemo.getProp().getProperty("SPORTS_APING_V1_0") + operation);
	/*	request.setParams(params);

		requestString =  JsonConverter.convertToJson(request);
		if(ApiNGDemo.isDebug())
			System.out.println("\nRequest: "+requestString);

		//We need to pass the "sendPostRequest" method a string in util format:  requestString
		HttpUtil requester = new HttpUtil();
		return requester.sendPostRequestJsonRpc(requestString, operation, appKey, ssoToken);
	   */
		return 1;
	}
	
	public String getHttpResponseWithSSL(String url) throws Exception {
		    //default truststore parameters
		   // System.setProperty("javax.net.ssl.trustStore", "/usr/lib/jvm/java-6-openjdk/jre/lib/securitycacerts");
		   // System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
		   // System.setProperty("javax.net.ssl.trustStoreType", "JKS");

		    //my certificate and password
		    System.setProperty("javax.net.ssl.keyStore", "mycert.pfx");
		    System.setProperty("javax.net.ssl.keyStorePassword", "mypass");
		    System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");


		/*    HttpClient httpclient = new HttpClient();

		    GetMethod method = new GetMethod();
		    method.setPath(url);

		    int statusCode = httpclient.executeMethod(method);
		    System.out.println("Status: " + statusCode);

		    method.releaseConnection();

		    return method.getResponseBodyAsString();
		    */ 
		    return null;
		}
		
	}
