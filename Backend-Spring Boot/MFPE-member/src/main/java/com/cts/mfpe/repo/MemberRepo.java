package com.cts.mfpe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.mfpe.model.Member;

@Repository
public interface MemberRepo extends JpaRepository<Member, Integer> {

}
