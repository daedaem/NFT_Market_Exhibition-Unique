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
public class Market {
	
	@Id
	@Column(name = "MARKET_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long marketId;
	
	@Column(name = "NFT_SEQ")
	private Long nftSeq;
	
	@Column(name = "MARKET_CONTRACT_ADDRESS")
	private String marketContractAddress;
	
	@Column(name = "SELLER")
	private String seller;
	
	@Column(name = "BUYER")
	private String buyer;
	
	@Column(name = "PRICE")
	private Long price;

	@Column(name = "START_TIME")
	private Date startTime;
	
	@Column(name = "END_TIME")
	private Date endTime;
	
	@CreationTimestamp
	@Column(name = "REG_DT")
	private Date regDt;
	
	@Column(name = "ENDED")
	private boolean ended;
	
	@Column(name = "CANCELED")
	private boolean canceled;
	
}
