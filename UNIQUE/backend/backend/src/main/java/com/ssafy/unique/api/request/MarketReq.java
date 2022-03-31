package com.ssafy.unique.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MarketReq {
	private Long nftSeq;
	private String marketContractAddress;
	private Long price;
	
	private String startTime;
	private String endTime;
}
