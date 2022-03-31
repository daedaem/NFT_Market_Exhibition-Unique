package com.ssafy.unique.api.service;

import com.ssafy.unique.api.request.MarketParamReq;
import com.ssafy.unique.api.request.MarketReq;
import com.ssafy.unique.api.request.MarketUpdateReq;
import com.ssafy.unique.api.response.MarketResultRes;
import com.ssafy.unique.api.response.ResultRes;

public interface MarketService {
	public MarketResultRes marketTotalSearch(MarketParamReq marketParamReq);
	public MarketResultRes marketDetailSearch(Long marketId);
	public ResultRes marketRegister(MarketReq marketReq);
	public ResultRes marketPurchase(MarketUpdateReq marketUpdateReq);
	public ResultRes marketCancel(MarketUpdateReq marketUpdateReq);
}
