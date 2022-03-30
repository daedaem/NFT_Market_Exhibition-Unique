package com.ssafy.unique.db.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@DynamicInsert
@DynamicUpdate
public class Nft {
	
	@Id
	@Column(name = "NFT_SEQ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nftSeq;
	
	@Column(name = "NFT_AUTHOR_SEQ")
	private Long nftAuthorSeq;
	
	@Column(name = "NFT_AUTHOR_NAME")
	private String nftAuthorName;
	
	@Column(name = "NFT_OWNER_SEQ")
	private Long nftOwnerSeq;
	
	@Column(name = "NFT_OWNER_ADDRESS")
	private String nftOwnerAddress;
	
	@Column(name = "NFT_WORK_URI")
	private String nftWorkUri;
	
	@Column(name = "NFT_METADATA_URI")
	private String nftMetadataUri;
	
	@Column(name = "NFT_TOKEN_ID")
	private Long nftTokenId;
	
	@Column(name = "NFT_NAME")
	private String nftName;
	
	@Column(name = "NFT_TYPE")
	private String nftType;
	
	@Column(name = "NFT_DESCRIPTION")
	private String nftDescription;
	
	@Column(name = "NFT_CONTRACT_ADDRESS")
	private String nftContractAddress;
	
	@CreationTimestamp
	@Column(name = "REG_DT")
	private Date regDt;
}
