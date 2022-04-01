package com.ssafy.unique.db.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

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
public class Exhibition {
	
	@Id
	@Column(name = "EXHIBITION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long exhibitionId;
	
	@Column(name = "MEMBER_SEQ")
	private Long memberSeq;
	
	@Column(name = "EXHIBITION_TITLE")
	private String exhibitionTitle;
	
	@Column(name = "EXHIBITION_TYPE")
	private String exhibitionType;
	
	@Column(name = "EXHIBITION_DESCRIPTION")
	private String exhibitionDescription;
	
	@CreationTimestamp
	@Column(name = "REG_Dt")
	private Date regDt;
}




