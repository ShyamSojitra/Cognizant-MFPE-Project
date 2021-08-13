package com.cts.mfpe.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Policy {
	
	@Id
	private int policyId;
	private int benefits;
	private int premium;
	private int tenure;

}
