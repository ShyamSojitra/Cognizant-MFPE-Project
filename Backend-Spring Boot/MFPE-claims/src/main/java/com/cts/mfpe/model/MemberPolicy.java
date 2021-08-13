package com.cts.mfpe.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MemberPolicy {
	
	@Id 
	private int memberId;
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate subsDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate premiumDueDate;
	private int capAmount;
	private int policyId;
}
