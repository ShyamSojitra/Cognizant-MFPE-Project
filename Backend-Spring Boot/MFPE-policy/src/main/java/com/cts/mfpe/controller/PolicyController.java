package com.cts.mfpe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mfpe.feign.AuthClient;
import com.cts.mfpe.model.Hospital;
import com.cts.mfpe.model.Policy;
import com.cts.mfpe.service.PolicyServiceImpl;

@RestController
@CrossOrigin(origins = "*")
public class PolicyController {

	@Autowired
	PolicyServiceImpl service;

	@Autowired
	private AuthClient client;
	
	@GetMapping("/getchainofproviders/{policyId}")
	public List<Hospital> getChainOfProviders( @PathVariable int policyId,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception {
		if(client.authorizeTheRequest(requestTokenHeader)) {
			return service.findAllHospitals(policyId);
		}else {
			throw new Exception("no Hospitals");
		}
	}
	@GetMapping("/getEligibleBenefits/{policyId}/{memberId}")
	public Policy getEligibleBenefits(@PathVariable int memberId,@PathVariable int policyId,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception{
		if(client.authorizeTheRequest(requestTokenHeader)) {
			return service.getEligibleBenefits(policyId, memberId);
		}else {
			throw new Exception("no Benefits");
		}
	}
	@GetMapping("/getEligibleClaimAmount/{policyId}/{memberId}/{benefits}")
	public int getEligibleClaimAmount(@PathVariable int benefits,@PathVariable int policyId,@PathVariable int memberId,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception{
		if(client.authorizeTheRequest(requestTokenHeader)) {
			return service.getEligibleClaimAmount(policyId, memberId, benefits);
		}else {
			throw new Exception("Can't claim");
		}
	}
}
