package com.cts.mfpe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.mfpe.model.Claim;

@Repository
public interface ClaimRepo extends JpaRepository<Claim, Integer> {

}
