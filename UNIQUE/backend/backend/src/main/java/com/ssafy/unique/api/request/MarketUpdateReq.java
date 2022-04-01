package com.ssafy.unique.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketUpdateReq {
	private Long marketId;
	private Long nftSeq;
}
