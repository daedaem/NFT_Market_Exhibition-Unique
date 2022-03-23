package com.ssafy.unique.api.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.unique.api.request.NftReq;

public interface IPFSService {
	
	String saveFile(NftReq nftReq, MultipartHttpServletRequest request);
	byte[] LoadFile(String hash);
}
