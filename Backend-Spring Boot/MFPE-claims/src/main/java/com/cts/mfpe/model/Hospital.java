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
public class Hospital {
	
	@Id
	private int providerId;
	private String hospitalName;
	private int policyId;
	private String location;
	
}
