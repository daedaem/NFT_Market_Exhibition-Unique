package com.ssafy.unique.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketParamReq {
	
	private int limit;
	private int offset;
	
	private String type;
	private String searchWord;
}
