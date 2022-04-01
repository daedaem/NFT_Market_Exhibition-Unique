package com.ssafy.unique.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class ExhibitionWork {
	
	@Id
	@Column(name = "WORK_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long workId;
	
	@Column(name = "EXHIBITION_ID")
	private Long exhibitionId;
	
	@Column(name = "WORK_COMMENT")
	private String workComment;
	
	@ManyToOne(targetEntity=Nft.class)
	@JoinColumn(name="NFT_SEQ")
	private Nft nft;
}
