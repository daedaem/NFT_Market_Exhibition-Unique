package com.ssafy.unique.api.service;

import com.ssafy.unique.api.request.ExhibitionParamReq;
import com.ssafy.unique.api.request.ExhibitionReq;
import com.ssafy.unique.api.response.ExhibitionRes;
import com.ssafy.unique.api.response.ExhibitionResultRes;
import com.ssafy.unique.api.response.ResultRes;

public interface ExhibitionService {
	public ResultRes exhibitionRegister(ExhibitionReq exhibitionReq);
	public ExhibitionResultRes exhibitionTotalSearch(ExhibitionParamReq exhibitionParamReq);
	public ExhibitionRes exhibitionDetailSearch(Long exhibitionId);
}
