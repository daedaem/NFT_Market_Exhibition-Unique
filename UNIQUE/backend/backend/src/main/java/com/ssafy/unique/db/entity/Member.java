package com.ssafy.unique.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Builder
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
}
