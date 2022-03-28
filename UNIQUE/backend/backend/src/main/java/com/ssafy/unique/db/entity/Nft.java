package com.ssafy.unique.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
<<<<<<< HEAD
	@Column(name = "NFT_AUTHOR_SEQ") //이거 NFT_AUTHOR_SEQ 아닌가?
	private Long nftAuthorSeq;

=======
>>>>>>> a0491097dce360acc914706ec6b79fcbc2de4a93
	@Column(name = "NFT_OWNER_ADDRESS")
	private String nftOwnerAddress;
	
	
	@Column(name = "NFT_WORK_URI")
	private String nftWorkUri;
	@Column(name = "NFT_METADATA_URI")
	private String nftMetadataUri;
	@Column(name = "NFT_TOKEN_ID")
	private Long nftTokenId;
	
<<<<<<< HEAD
	@Column(name = "NFT_HASH")
	private String nftHash;

	@Column(name = "NFT_DESCRIPTION")
	private String nftDescription;

=======
	
	@Column(name = "NFT_NAME")
	private String nftName;

	@Column(name = "NFT_TYPE")
	private String nftType;
	
	@Column(name = "NFT_DESCRIPTION")
	private String nftDescription;
>>>>>>> a0491097dce360acc914706ec6b79fcbc2de4a93
	
}
