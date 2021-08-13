package com.cts.mfpe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.mfpe.model.Policy;

@Repository
public interface PolicyRepo extends JpaRepository<Policy,Integer> {

}
