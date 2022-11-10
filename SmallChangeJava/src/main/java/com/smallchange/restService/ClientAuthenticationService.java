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

import com.smallchange.integration.ClientAuthenticationMyBatisDao;
import com.smallchange.integration.TradeHistoryMyBatisDao;
import com.smallchange.uimodel.ClientSendBackDetails;
import com.smallchange.uimodel.LoginClientDetails;
import com.smallchange.uimodel.RegistrationClientDetails;
import com.smallchange.uimodel.TradeHistory;

@RestController
@CrossOrigin("*")
@RequestMapping("/clientAuthentication")
public class ClientAuthenticationService {
	
	@Autowired
	private ClientAuthenticationMyBatisDao dao;
	
	@Autowired
	Logger logger;
	
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ClientSendBackDetails getClientIdFromLoginCredentials(@RequestBody LoginClientDetails loginClientDetails) {
		
		ClientSendBackDetails clientSendBackDetails = new ClientSendBackDetails();
		
//		if (id <= 0) {
//			logger.error("negative id received");
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
//		}
		
		try {
			clientSendBackDetails = dao.loginAuthenticationService(loginClientDetails);

		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server side error", e);
		}
		
		if (clientSendBackDetails != null) {
			logger.info("Successful retrieval");
			clientSendBackDetails.setMessage("Fetched successfully");
		} else {
			clientSendBackDetails = new ClientSendBackDetails();
			clientSendBackDetails.setMessage("Client Id and password do not match");
			logger.error("Client Id and password do not match");
			//throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No trade history in the db with id = " );
		}
		return clientSendBackDetails;
	}
	
	@PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE)
	public ClientSendBackDetails queryTradeHistoryByClientId(@RequestBody RegistrationClientDetails registrationClientDetails) {
		ResponseEntity<List<TradeHistory>> result;
		ClientSendBackDetails clientSendBackDetails =  new ClientSendBackDetails();
		
//		if (id <= 0) {
//			logger.error("negative id received");
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
//		}
		
		ClientSendBackDetails test = new ClientSendBackDetails();
		test = dao.checkClientMailList(registrationClientDetails.getClientMail());
		if(test !=null) {
			test.setMessage("Already Present");
			return test;
		}
		
		
		try {
			clientSendBackDetails = dao.registrationAuthenticationService(registrationClientDetails);

		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server side error", e);
		}
		
		if (clientSendBackDetails != null) {
			logger.info("Successfully registered");
			//clientSendBackDetails.setMessage("Fetched successfully");
		} else {
			//clientSendBackDetails.setMessage("Client mail and password do not match ");
			logger.error("No trade history in the db with that client id");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No trade history in the db with id = " );
		}
		return clientSendBackDetails;
	}
	
	

}
