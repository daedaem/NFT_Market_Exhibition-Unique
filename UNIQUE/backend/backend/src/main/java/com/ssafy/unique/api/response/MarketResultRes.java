package com.ssafy.unique.api.response;

import java.util.List;

import com.ssafy.unique.db.entity.Market;
import com.ssafy.unique.db.entity.Nft;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketResultRes {

	private int result;
	private Market market;
	private Nft nft;
	
	private List<Market> marketList;
	private List<Nft> nftList;
	
}
