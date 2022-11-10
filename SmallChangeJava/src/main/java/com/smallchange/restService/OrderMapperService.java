package com.smallchange.restService;



import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.smallchange.exception.InsufficientFundsException;
import com.smallchange.integration.OrderDaoMyBatisImpl;
import com.smallchange.integration.TradeHistoryMyBatisDao;
import com.smallchange.uimodel.BuyInstrument;
import com.smallchange.uimodel.BuyOrder;
import com.smallchange.uimodel.BuySellResponse;
import com.smallchange.uimodel.SellInstrument;
import com.smallchange.uimodel.SellOrder;
import com.smallchange.uimodel.TradeHistory;

@RestController
@CrossOrigin("*")
@RequestMapping("/orderMapper")
public class OrderMapperService {
	
	@Autowired
	private OrderDaoMyBatisImpl dao;
	
	@Autowired
	Logger logger;
	
	@GetMapping(value = "/sell/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SellInstrument>> querySellInstrument(@PathVariable String id) {
		ResponseEntity<List<SellInstrument>> result;
		List<SellInstrument> sellInstrument;
		if (id == null) {
			logger.error("null id received");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
		}
		try {
			sellInstrument = dao.getSellInstrument(id);

		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server side error", e);
		}
		System.out.println("Size"+sellInstrument.size());
		if (sellInstrument != null && sellInstrument.size()>0) {
			logger.info("Successful retrieval");
			result = ResponseEntity.ok(sellInstrument);
			System.out.println(result.getBody());
		} else {
			logger.error("No Sell instrument");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No sell instrument");
		}
		return result;
	}
	
	@GetMapping(value = "/buy", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BuyInstrument>> queryBuyInstrument() {
		ResponseEntity<List<BuyInstrument>> result;
		List<BuyInstrument> buyInstrument;
		try {
			buyInstrument = dao.getBuyInstrument();

		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server side error", e);
		}
		System.out.println("Size"+buyInstrument.size());
		if (buyInstrument != null && buyInstrument.size()>0) {
			logger.info("Successful retrieval");
			result = ResponseEntity.ok(buyInstrument);
			System.out.println(result.getBody());
		} else {
			logger.error("No Buy instrument");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Buy instrument");
		}
		return result;
	}
	
	@PostMapping(path="buyUpdate",consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BuySellResponse> queryPutBuyTrade(@RequestBody BuyOrder buyorder) throws InsufficientFundsException {
		System.out.println("Hello seneha 1");
		Boolean response=dao.putBuyTrade(buyorder);
		BuySellResponse buySellResponse=new BuySellResponse();
		buySellResponse.setResponse(response);
		System.out.println(response);
		return new ResponseEntity<>(buySellResponse, HttpStatus.CREATED);
	}
	@PostMapping(path="sellUpdate",consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BuySellResponse> queryPutSellTrade(@RequestBody SellOrder sellorder) throws InsufficientFundsException {
		System.out.println("Hello seneha 2");
		Boolean response=dao.putSellTrade(sellorder);
		BuySellResponse buySellResponse=new BuySellResponse();
		buySellResponse.setResponse(response);
		System.out.println(response);
		return new ResponseEntity<>(buySellResponse, HttpStatus.CREATED);
	}
	
//	@GetMapping(value = "/{id}/Sell", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<TradeHistory>> queryTradeHistoryByClientIdSell(@PathVariable int id) {
//		ResponseEntity<List<TradeHistory>> result;
//		List<TradeHistory> tradeHistory;
//		
//		if (id <= 0) {
//			logger.error("negative id received");
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
//		}
//		
//		try {
//			tradeHistory = dao.getTradeHistoryBySell(Integer.toString(id));
//
//		} catch (RuntimeException e) {
//			logger.error(e.getMessage());
//			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server side error", e);
//		}
//		System.out.println("Size"+tradeHistory.size());
//		if (tradeHistory != null && tradeHistory.size()>0) {
//			logger.info("Successful retrieval");
//			result = ResponseEntity.ok(tradeHistory);
//			System.out.println(result.getBody());
//		} else {
//			logger.error("No trade history in the db with that client id");
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No trade history in the db with id = " + id);
//		}
//		return result;
//	}



}
