package uk.co.lnssolutions.fortune.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.co.lnssolutions.fortune.constants.Constants;
import uk.co.lnssolutions.fortune.constants.Fortune;
import uk.co.lnssolutions.fortune.containers.EventTypeResultContainer;
import uk.co.lnssolutions.fortune.containers.ListMarketBooksContainer;
import uk.co.lnssolutions.fortune.containers.ListMarketCatalogueContainer;
import uk.co.lnssolutions.fortune.containers.PlaceOrdersContainer;
import uk.co.lnssolutions.fortune.containers.account.AccountFundsResponseContainer;
import uk.co.lnssolutions.fortune.containers.account.AccountStatementContainer;
import uk.co.lnssolutions.fortune.entities.EventTypeResult;
import uk.co.lnssolutions.fortune.entities.MarketBook;
import uk.co.lnssolutions.fortune.entities.MarketCatalogue;
import uk.co.lnssolutions.fortune.entities.MarketFilter;
import uk.co.lnssolutions.fortune.entities.PlaceExecutionReport;
import uk.co.lnssolutions.fortune.entities.PlaceInstruction;
import uk.co.lnssolutions.fortune.entities.PriceProjection;
import uk.co.lnssolutions.fortune.entities.TimeRange;
import uk.co.lnssolutions.fortune.entities.account.AccountFundsResponse;
import uk.co.lnssolutions.fortune.entities.account.AccountStatementReport;
import uk.co.lnssolutions.fortune.entities.account.StatementItem;
import uk.co.lnssolutions.fortune.enums.ApiNgOperation;
import uk.co.lnssolutions.fortune.enums.IncludeItem;
import uk.co.lnssolutions.fortune.enums.MarketProjection;
import uk.co.lnssolutions.fortune.enums.MarketSort;
import uk.co.lnssolutions.fortune.enums.MatchProjection;
import uk.co.lnssolutions.fortune.enums.OrderProjection;
import uk.co.lnssolutions.fortune.enums.Wallet;
import uk.co.lnssolutions.fortune.exceptions.APINGException;
import uk.co.lnssolutions.fortune.json.utils.JsonConverter;
import uk.co.lnssolutions.fortune.json.utils.JsonrpcRequest;


public class ApiNgJsonRpcOperations extends ApiNgOperations{

	private boolean debug;
	
    private static ApiNgJsonRpcOperations instance = null;

    private ApiNgJsonRpcOperations(){}

    public static ApiNgJsonRpcOperations getDebugInstance(){
        if (instance == null){
            instance = new ApiNgJsonRpcOperations();
            instance.setDebug(true);
        }
        return instance;
    }
    
    
    public static ApiNgJsonRpcOperations getInstance(){
        if (instance == null){
            instance = new ApiNgJsonRpcOperations();
            instance.setDebug(false);
        }
        return instance;
    }
    
    public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public List<EventTypeResult> listEventTypes(MarketFilter filter, String appKey, String ssoId) throws APINGException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(FILTER, filter);
        params.put(LOCALE, locale);
        String result = getInstance().makeExchangeRequest(ApiNgOperation.LISTEVENTTYPES.getOperationName(), params, appKey, ssoId);
      // if(debug)
            System.out.println("\nResponse: "+result);

        EventTypeResultContainer container = JsonConverter.convertFromJson(result, EventTypeResultContainer.class);
        if(container.getError() != null)
            throw container.getError().getData().getAPINGException();

        return container.getResult();

    }

    public List<MarketBook> listMarketBook(List<String> marketIds, PriceProjection priceProjection, OrderProjection orderProjection,
                                           MatchProjection matchProjection, String currencyCode, String appKey, String ssoId) throws APINGException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(LOCALE, locale);
        params.put(MARKET_IDS, marketIds);
        params.put(PRICE_PROJECTION, priceProjection);
        params.put(ORDER_PROJECTION, orderProjection);
        params.put(MATCH_PROJECTION, matchProjection);
        params.put("currencyCode", currencyCode);
        String result = getInstance().makeExchangeRequest(ApiNgOperation.LISTMARKETBOOK.getOperationName(), params, appKey, ssoId);
        if(debug)
            System.out.println("\nResponse: "+result);

        ListMarketBooksContainer container = JsonConverter.convertFromJson(result, ListMarketBooksContainer.class);

        if(container.getError() != null)
            throw container.getError().getData().getAPINGException();

        return container.getResult();


    }

    public List<MarketCatalogue> listMarketCatalogue(MarketFilter filter, Set<MarketProjection> marketProjection,
                                                     MarketSort sort, String maxResult, String appKey, String ssoId) throws APINGException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(LOCALE, locale);
        params.put(FILTER, filter);
        params.put(SORT, sort);
        params.put(MAX_RESULT, maxResult);
        params.put(MARKET_PROJECTION, marketProjection);
        String result = getInstance().makeExchangeRequest(ApiNgOperation.LISTMARKETCATALOGUE.getOperationName(), params, appKey, ssoId);
        if(debug)
            System.out.println("\nResponse: "+result);

        ListMarketCatalogueContainer container = JsonConverter.convertFromJson(result, ListMarketCatalogueContainer.class);

        if(container.getError() != null)
            throw container.getError().getData().getAPINGException();

        return container.getResult();

    }

    public PlaceExecutionReport placeOrders(String marketId, List<PlaceInstruction> instructions, String customerRef , String appKey, String ssoId) throws APINGException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(LOCALE, locale);
        params.put(MARKET_ID, marketId);
        params.put(INSTRUCTIONS, instructions);
        params.put(CUSTOMER_REF, customerRef);
        String result = getInstance().makeExchangeRequest(ApiNgOperation.PLACORDERS.getOperationName(), params, appKey, ssoId);
        if(debug)
            System.out.println("\nResponse: "+result);

        PlaceOrdersContainer container = JsonConverter.convertFromJson(result, PlaceOrdersContainer.class);

        if(container.getError() != null)
            throw container.getError().getData().getAPINGException();

        return container.getResult();

    }

	//  So let's see what can be built on
	public  AccountFundsResponse GetAccountFunds(String wallet,String appKey,String ssoId) throws APINGException{
        Map<String, Object> params = new HashMap<String, Object>();
         params.put(LOCALE, locale);
      //  params.put(MARKET_ID, marketId);
      //  params.put(INSTRUCTIONS, instructions);
        params.put(WALLET,Wallet.UK );
        
        String result = getInstance().makeAccountRequest(ApiNgOperation.GETACCOUNTFUNDS.getOperationName(), params, appKey, ssoId);
       // if(debug)
            System.out.println("\nResponse: "+result);

//        PlaceOrdersContainer container = JsonConverter.convertFromJson(result, PlaceOrdersContainer.class);
  
         AccountFundsResponseContainer container = JsonConverter.convertFromJson(result, AccountFundsResponseContainer.class);
         if(container.getError() != null)
            throw container.getError().getData().getAPINGException();

         
         
        return container.getResult();
	 // return container;
	}
    
	public  AccountStatementReport GetAccountStatement(String locale,int fromRecord, int recordCount,TimeRange dateRange,IncludeItem includeItem,Wallet wallet,String appKey,String ssoId) throws APINGException
	{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(LOCALE, locale);
      //  params.put(MARKET_ID, marketId);
      //  params.put(INSTRUCTIONS, instructions);
      // params.put(WALLET,Wallet.AUSTRALIAN );
        String resultStr = getInstance().makeAccountRequest(ApiNgOperation.GETACCOUNTSTATEMENT.getOperationName(), params, appKey, ssoId);
       // if(debug)
            System.out.println("\nResponse :"+resultStr);

//        PlaceOrdersContainer container = JsonConverter.convertFromJson(result, PlaceOrdersContainer.class);
  
         AccountStatementContainer container = JsonConverter.convertFromJson(resultStr, AccountStatementContainer.class);
         if(container.getError() != null)
            throw container.getError().getData().getAPINGException();
         
        // So have we parse the data then ?
         AccountStatementReport test = container.getResult();
         if (test != null) System.out.println("Statement Report is not null");
         else System.out.println("Statement Report is null");
         
         if (test != null) System.out.println(test.report());
         
         List statementItems = test.getAccountStatement();
         if (statementItems == null) System.out.println("We have items of some descripgtion");
        return container.getResult();	
    
	}
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////
	//
	//  Two protected methods for calling Betfair - note API endpoint change
	
	
    protected String makeExchangeRequest(String operation, Map<String, Object> params, String appKey, String ssoToken) {
        String requestString;
        //Handling the JSON-RPC request
        JsonrpcRequest request = new JsonrpcRequest();
        request.setId("1"); //TODO Do we want this ? UK / AUD wallets ?
        request.setMethod(Fortune.getProp().getProperty("SPORTS_APING_V1_0") + operation);
        request.setParams(params);

        requestString =  JsonConverter.convertToJson(request);
      //  if(debug)
            System.out.println("\nRequest: "+requestString);

        //We need to pass the "sendPostRequest" method a string in util format:  requestString
        HttpUtil requester = new HttpUtil();
        return requester.sendPostRequestJsonRpc(requestString, operation, appKey, ssoToken);

       }
    
    protected String makeAccountRequest(String operation, Map<String, Object> params, String appKey, String ssoToken) {
        String requestString;
        //Handling the JSON-RPC request
        JsonrpcRequest request = new JsonrpcRequest();
        request.setId("1");  //TODO Do we want this ? UK / AUD wallets ?
        request.setMethod(Fortune.getProp().getProperty("ACCOUNT_APING_V1_0") + operation);
        request.setParams(params);

        requestString =  JsonConverter.convertToJson(request);
      //  if(debug)
            System.out.println("\nRequest: "+requestString);

        //We need to pass the "sendPostRequest" method a string in util format:  requestString
        HttpUtil requester = new HttpUtil();
        return requester.sendPostAccountRequestJsonRpc(requestString, operation, appKey, ssoToken);

       }

}

