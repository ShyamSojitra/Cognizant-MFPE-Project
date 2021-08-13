package com.cts.mfpe.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "member_policy")
public class MemberPolicy {
	
	@Id 
	@GeneratedValue
	private int memberId;
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate subsDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate premiumDueDate;
	private int capAmount;
	private int policyId;
}
