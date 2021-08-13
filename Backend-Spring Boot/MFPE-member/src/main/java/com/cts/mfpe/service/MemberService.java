package com.cts.mfpe.service;

import java.util.List;

import com.cts.mfpe.model.MemberPolicy;

public interface MemberService {

	List<MemberPolicy> viewBills(int policyId,int memberId);
}
