package com.ssafy.unique.api.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.unique.api.request.NftReq;
import com.ssafy.unique.api.request.NftUpdateReq;
import com.ssafy.unique.api.response.NftRes;
import com.ssafy.unique.api.response.ResultRes;

public interface IPFSService {
	
	NftRes saveFile(NftReq nftReq, MultipartHttpServletRequest request);
	byte[] LoadFile(String hash);
	ResultRes updateNFT(NftUpdateReq nftUpdateReq);
}
