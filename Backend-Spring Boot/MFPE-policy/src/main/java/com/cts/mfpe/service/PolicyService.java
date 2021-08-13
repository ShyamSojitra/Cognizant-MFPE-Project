package com.cts.mfpe.service;

import java.util.List;

import com.cts.mfpe.model.Hospital;
import com.cts.mfpe.model.Policy;

public interface PolicyService {

	List<Hospital> findAllHospitals(int policyId);

	Policy getEligibleBenefits(int policyId, int memberId);

	int getEligibleClaimAmount(int policyId, int memberId, int benefits);
}
