package com.ssafy.unique.db.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	
	@Id
	@Column(name = "MEMBER_SEQ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberSeq;
	
	@Column(name = "MEMBER_ID")
	private String memberId;
	
	@Column(name = "MEMBER_PASSWORD")
	private String memberPassword;

	@Column(name = "MEMBER_ADDRESS")
	private String memberAddress;

	@Column(name = "PROFILE_IMAGE_URL")
	private String profileImageUrl;
	
	@Column(name = "AUTHORITY")
	private boolean authority;
	
	@Column(name = "MEMBER_BIO")
	private String memberBio;
	
	@Column(name = "SSF")
	private Long ssf;
	
	@CreationTimestamp
	@Column(name = "REG_DT")
	private Date regDt;
}
