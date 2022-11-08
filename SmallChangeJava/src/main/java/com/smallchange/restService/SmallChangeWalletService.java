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

import com.smallchange.integration.SmallChangeWalletMyBatisDao;
import com.smallchange.uimodel.BankAccountDetails;
import com.smallchange.uimodel.SmallChangeWalletAmount;
import com.smallchange.uimodel.TradeHistory;
import com.smallchange.uimodel.WalletUpdateDetails;
import com.smallchange.uimodel.WalletUpdateValues;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/smallChangeWallet")
public class SmallChangeWalletService {
	
	@Autowired
	private SmallChangeWalletMyBatisDao dao;
	
	@Autowired
	Logger logger;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public SmallChangeWalletAmount getSmallChangeWalletBalance(@PathVariable int id) {
		SmallChangeWalletAmount amount = new SmallChangeWalletAmount();
		
		try {
			amount = dao.getUserSmallChangeWalletAmount(Integer.toString(id));

		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server side error", e);
		}
		if (amount != null) {
			logger.info("Successful retrieval");
		} else {
			logger.error("No trade history in the db with that client id");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No trade history in the db with id = " + id);
		}
		return amount;
	}
	
	@GetMapping(value = "/getAccount/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BankAccountDetails>> getAccountDetails(@PathVariable int id) {
		ResponseEntity<List<BankAccountDetails>> result;
		List<BankAccountDetails> bankAccountDetails;
		
		if (id <= 0) {
			logger.error("negative id received");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
		}
		
		try {
			bankAccountDetails = dao.getBankAccountDetails(Integer.toString(id));

		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server side error", e);
		}
		
		if (bankAccountDetails != null) {
			logger.info("Successful retrieval");
			result = ResponseEntity.ok(bankAccountDetails);
			System.out.println(result.getBody());
		} else {
			logger.error("No trade history in the db with that client id");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No trade history in the db with id = " + id);
		}
		return result;
	}
	
	@PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public WalletUpdateDetails updateSmallChangeWalletBalance(@RequestBody WalletUpdateValues walletUpdateValues) {
		WalletUpdateDetails details = new WalletUpdateDetails();
		
		try {
			details = dao.updateSmallChangeWallet(walletUpdateValues);

		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server side error", e);
		}
		if (details != null) {
			logger.info("Successful retrieval");
		} else {
			logger.error("No trade history in the db with that client id");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No trade history in the db with id = " );
		}
		return details;
	}
	
	

}