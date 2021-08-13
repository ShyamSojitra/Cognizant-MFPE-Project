package com.cts.mfpe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mfpe.feign.AuthClient;
import com.cts.mfpe.feign.ClaimClient;
import com.cts.mfpe.model.Claim;
import com.cts.mfpe.model.MemberPolicy;
import com.cts.mfpe.service.MemberServiceImpl;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemberController {

	@Autowired
	MemberServiceImpl service;

	@Autowired
	AuthClient client;

	@Autowired
	ClaimClient claimClient;

	@GetMapping("/viewbills/{memberId}/{policyId}")
	public List<MemberPolicy> viewBills(@PathVariable int policyId, @PathVariable int memberId,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception {
		if (client.authorizeTheRequest(requestTokenHeader)) {
			return service.viewBills(policyId, memberId);
		} else {
			throw new Exception("no bills");
		}
	}

	@GetMapping("/getclaimstatus/{memberId}/{policyId}/{claimId}")
	public Claim getClaimStatus(@PathVariable int policyId, @PathVariable int memberId, @PathVariable int claimId,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) {
		return claimClient.getClaimStatus(policyId, memberId, claimId, requestTokenHeader);
	}

	@PostMapping("/submitclaim/{memberId}/{policyId}")
	public Boolean submitClaim(@PathVariable int policyId, @PathVariable int memberId, @RequestBody int amount,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) {
		System.out.print("ohooo");
		Claim claim = new Claim();
		claim.setAmountClaimed(amount);
		claim.setMemberId(memberId);
		claimClient.submitClaim(policyId, memberId, claim, requestTokenHeader);
		return true;
	}
}
