package com.cts.mfpe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.mfpe.feign.PolicyClient;
import com.cts.mfpe.model.Claim;
import com.cts.mfpe.model.Policy;
import com.cts.mfpe.repo.ClaimRepo;

@Service
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	ClaimRepo claimRepo;

	@Autowired
	PolicyClient policyClient;

	@Override
	public Claim getClaimStatus(int claimId, int policyId, int memberId, String tkn) {
		List<Claim> claims = claimRepo.findAll();
		Policy policy = policyClient.getEligibleBenefits(memberId, policyId, tkn);
		for (Claim c : claims) {
			if (c.getClaimId() == claimId && policy.getPolicyId() == policyId) {
				return c;
			}
		}
		return null;
	}

	@Override
	public Claim submitClaim(int policyId, int memberId, Claim claim, String tkn) {
		int sum = 0;
		List<Claim> claims = claimRepo.findAll();
		if (claims != null) {
			for (Claim c : claims) {
				if (memberId == c.getMemberId()) {
					sum = sum + c.getAmountClaimed();
				}
			}
		}
		sum = sum + policyClient.getEligibleBenefits(memberId, policyId, tkn).getBenefits();
		if (policyClient.getEligibleClaimAmount(claim.getAmountClaimed(), policyId, memberId, tkn) < sum) {
			claim.setStatus("Pending Action");
			claim.setDescription("Wait for updates");
			claim.setSetteled(claim.getAmountClaimed());
			// claim.setMemberId(memberId);
			claimRepo.save(claim);
			return claim;
		} else {
			claim.setStatus("Claim Rejected");
			claim.setDescription("Under Dispute");
			claim.setSetteled(0);
			// claim.setMemberId(memberId);
			claimRepo.save(claim);
			return claim;
		}
	}

}
