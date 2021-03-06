package com.meritbank.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritbank.exceptions.NoResourceFoundException;
import com.meritbank.model.AccountHolder;
import com.meritbank.model.ContactDetails;
import com.meritbank.service.AccountHolderService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class AccountHolderController {
	Logger logs = LoggerFactory.getLogger(AccountHolderController.class);

	@Autowired
	private AccountHolderService accountHolderService;

	@PostMapping("/accountholders")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public AccountHolder addAccountHolder(@RequestBody @Valid AccountHolder accountHolder) {
		return accountHolderService.addAccountHolder(accountHolder);
	}
	
	@DeleteMapping("/accountholders")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public AccountHolder deleteAccountHolder(@RequestBody @Valid AccountHolder accountHolder) throws NoResourceFoundException {
		return accountHolderService.deleteAccountHolder(accountHolder);
	}

	@GetMapping("/accountholders")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<AccountHolder> getAccountHolders() {
		return accountHolderService.getAccountHolders();
	}

	@GetMapping("/accountholders/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public AccountHolder getAccountHolders(@PathVariable("id") int id) {
		return accountHolderService.getAccountHolder(id);
	}

	@PostMapping("/accountholders/{id}/contactdetails")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ContactDetails addDetails(@PathVariable("id") int id,
			@RequestBody ContactDetails accountDetails) {
		return accountHolderService.addContactDetails(id, accountDetails);
	}
}
