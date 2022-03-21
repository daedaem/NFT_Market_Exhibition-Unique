package com.ssafy.unique.api.service;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.unique.api.request.NftReq;

public interface IPFSService {
	
	String saveFile(NftReq nftReq, MultipartFile file);
	byte[] LoadFile(String hash);
}
