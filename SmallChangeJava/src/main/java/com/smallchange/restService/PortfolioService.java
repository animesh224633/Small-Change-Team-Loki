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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.smallchange.integration.PortfolioMyBatisDao;
import com.smallchange.integration.TradeHistoryMyBatisDao;
import com.smallchange.uimodel.Portfolio;
import com.smallchange.uimodel.TradeHistory;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/portfolio")
public class PortfolioService {
	
	@Autowired
	private PortfolioMyBatisDao dao;
	
	@Autowired
	Logger logger;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Portfolio>> queryPortfolioByClientId(@PathVariable String id) {
		ResponseEntity<List<Portfolio>> result;
		List<Portfolio> portfolio;
		
		if (id == null) {
			logger.error("null id received");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
		}
		
		try {
			portfolio = dao.getUserPortfolio(id);

		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server side error", e);
		}
		System.out.println("Size"+portfolio.size());
		if (portfolio != null && portfolio.size()>0) {
			logger.info("Successful retrieval");
			result = ResponseEntity.ok(portfolio);
			System.out.println(result.getBody());
		} else {
//			logger.error("No trade history in the db with that client id");
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No trade history in the db with id = " + id);
			logger.info("Empty List retrieved");
			result = ResponseEntity.ok(portfolio);
			System.out.println(result.getBody());
		}
		return result;
	}

}
