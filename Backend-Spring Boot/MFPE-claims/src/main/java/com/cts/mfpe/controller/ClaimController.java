package com.cts.mfpe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mfpe.model.Claim;
import com.cts.mfpe.service.ClaimServiceImpl;

@RestController
@CrossOrigin(origins = "*")
public class ClaimController {

	@Autowired
	ClaimServiceImpl service;
	
	
	@GetMapping("/getclaimstatus/{memberId}/{policyId}/{claimId}")
	public Claim  getClaimStatus(@PathVariable int policyId,@PathVariable int memberId,@PathVariable int claimId,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception{
		return service.getClaimStatus(claimId, policyId, memberId,requestTokenHeader);
	}
	
	@PostMapping("/submitclaim/{memberId}/{policyId}")
	public Claim  submitClaim(@PathVariable int policyId,@PathVariable int memberId,@RequestBody Claim claim,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception{
		return service.submitClaim(policyId, memberId, claim,requestTokenHeader);
	}
	
}
