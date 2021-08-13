package com.cts.mfpe.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.mfpe.model.MemberPolicy;
import com.cts.mfpe.repo.MemberPolicyRepo;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberPolicyRepo memberPolicyRepo;

	@Override
	public List<MemberPolicy> viewBills(int policyId, int memberId) {
		List<MemberPolicy> bills = new ArrayList<>();
		List<MemberPolicy> memberPolicies = memberPolicyRepo.findAll();
		for (MemberPolicy memberPolicy : memberPolicies) {
			if ((memberPolicy.getMemberId() == memberId) && (memberPolicy.getPolicyId() == policyId)) {
				bills.add(memberPolicy);
			}
		}
		if(!bills.isEmpty())
			return bills;
		else {
			return Collections.emptyList();
		}
	}
}
