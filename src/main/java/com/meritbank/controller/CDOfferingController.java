package com.meritbank.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritbank.exceptions.InvalidArgumentException;
import com.meritbank.exceptions.NotAuthorizedException;
import com.meritbank.model.CDOffering;
import com.meritbank.service.CDOfferingService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CDOfferingController {
	Logger logs = LoggerFactory.getLogger(BankAccountsController.class);

	@Autowired
	private CDOfferingService cdOfferingService;

	@GetMapping("/CDOfferings")
	public List<CDOffering> getCDOfferings() {
		return cdOfferingService.getCDOfferings();
	}
	
	@PostMapping("/CDOfferings")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public CDOffering addCDOffering(@RequestBody CDOffering cdOffering) throws NotAuthorizedException, InvalidArgumentException {
		return cdOfferingService.addCDOffering(cdOffering);
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/CDOfferings")
	public String clearCDOfferings() {
		return cdOfferingService.clearCDOffering();
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/CDOffering")
	public String deleteCDOfferings(@RequestBody CDOffering cdOffering) {
		return cdOfferingService.deleteCDOffering(cdOffering);
	}
}

