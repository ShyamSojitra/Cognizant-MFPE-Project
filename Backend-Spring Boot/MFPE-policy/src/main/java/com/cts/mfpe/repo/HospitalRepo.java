package com.cts.mfpe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.mfpe.model.Hospital;

@Repository
public interface HospitalRepo extends JpaRepository<Hospital, Integer> {

}
