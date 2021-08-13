package com.cts.mfpe.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.mfpe.model.Claim;

@FeignClient(name = "Claim-Microservice",url = "http://localhost:8200")
public interface ClaimClient {
	
	@GetMapping("/getclaimstatus/{memberId}/{policyId}/{claimId}")
	public Claim getClaimStatus(@PathVariable int policyId,@PathVariable int memberId,@PathVariable int claimId,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader);
	
	@PostMapping("/submitclaim/{memberId}/{policyId}")
	public Claim submitClaim(@PathVariable int policyId,@PathVariable int memberId,@RequestBody Claim claim,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader);

}
