package com.smallchange.restService;


import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.smallchange.integration.TradeHistoryMyBatisDao;
import com.smallchange.uimodel.TradeHistory;

@RestController
@RequestMapping("/tradeHistory")
public class TradeHistoryService {
	
	@Autowired
	private TradeHistoryMyBatisDao dao;
	
	@Autowired
	Logger logger;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TradeHistory>> queryTradeHistoryByClientId(@PathVariable int id) {
		ResponseEntity<List<TradeHistory>> result;
		List<TradeHistory> tradeHistory;
		
		if (id <= 0) {
			logger.error("negative id received");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
		}
		
		try {
			tradeHistory = dao.getTradeHistory(Integer.toString(id));

		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server side error", e);
		}
		System.out.println("Size"+tradeHistory.size());
		if (tradeHistory != null && tradeHistory.size()>0) {
			logger.info("Successful retrieval");
			result = ResponseEntity.ok(tradeHistory);
			System.out.println(result.getBody());
		} else {
			logger.error("No trade history in the db with that client id");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No trade history in the db with id = " + id);
		}
		return result;
	}

}
