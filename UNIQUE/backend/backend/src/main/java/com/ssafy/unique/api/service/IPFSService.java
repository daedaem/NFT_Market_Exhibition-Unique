package com.ssafy.unique.api.service;

import org.springframework.web.multipart.MultipartFile;

public interface IPFSService {
	
	String saveFile(MultipartFile file);
	byte[] LoadFile(String hash);
}
