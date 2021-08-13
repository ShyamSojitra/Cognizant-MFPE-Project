package com.cts.mfpe.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.mfpe.model.Hospital;
import com.cts.mfpe.model.Policy;

@FeignClient(name = "Policy-Microservice", url = "http://localhost:8300")
public interface PolicyClient {

	@GetMapping("/getchainofproviders/{policyId}")
	public List<Hospital> getChainOfProviders(@PathVariable int policyId,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader);

	@GetMapping("/getEligibleBenefits/{policyId}/{memberId}")
	public Policy getEligibleBenefits(@PathVariable int memberId, @PathVariable int policyId,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader);

	@GetMapping("/getEligibleClaimAmount/{policyId}/{memberId}/{benefits}")
	public int getEligibleClaimAmount(@PathVariable int benefits, @PathVariable int policyId,
			@PathVariable int memberId,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader);
}
