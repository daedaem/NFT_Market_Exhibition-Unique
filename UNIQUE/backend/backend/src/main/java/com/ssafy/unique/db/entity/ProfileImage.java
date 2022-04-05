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
public class ProfileImage {
	
	@Id
	@Column(name = "PROFILE_IMAGE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long profileImageId;
	
	@Column(name = "MEMBER_SEQ")
	private Long memberSeq;
	
	@Column(name = "PROFILE_IMAGE_NAME")
	private String profileImageName;
	
	@Column(name = "PROFILE_IMAGE_SIZE")
	private Long profileImageSize;
	
	@Column(name = "PROFILE_IMAGE_URL")
	private String profileImageUrl;
	
	@CreationTimestamp
	@Column(name = "REG_DT")
	private Date regDt;
}
