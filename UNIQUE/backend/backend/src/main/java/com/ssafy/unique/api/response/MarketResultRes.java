package com.ssafy.unique.api.response;

import java.util.List;

import com.ssafy.unique.db.entity.Market;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketResultRes {

	private int result;
	private int count;
	
	private Market market;
	private List<Market> marketList;
	
}
