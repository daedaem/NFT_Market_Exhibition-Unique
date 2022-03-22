package com.ssafy.unique.api.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.unique.api.request.NftReq;
import com.ssafy.unique.api.service.IPFSService;

@CrossOrigin(
		origins = { "http://localhost:5500", "http://172.30.1.59:5500", "http://192.168.0.100:5500", "http://192.168.0.40:5500","https://j6e205.p.ssafy.io" },
		allowCredentials = "true", // axios가 sessionId를 계속 다른것을 보내는데, 이것을 고정시켜준다
		allowedHeaders = "*",
		methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS })
@RestController
public class IPFSController {
	
	@Autowired
	private IPFSService ipfsService;
	
	@GetMapping(value="/hello")
	public String hello() {
		System.out.println("hello");
		return "hello";
	}
	
	@PostMapping(value="/file")
	public String saveFile(
			@ModelAttribute NftReq nftReq, 
			@RequestParam("file")MultipartFile file) {
		System.out.println("Upload 진입");
		return ipfsService.saveFile(nftReq, file);
	}
	
	@GetMapping(value="/file/{hash}")
	public ResponseEntity<byte[]> loadFile(@PathVariable("hash") String hash) {
		System.out.println("LoadFile 진입");
		HttpHeaders httpHeaders = new HttpHeaders();
		// ALL_VALUE 를 사용하면, byte[]가 깨져서 전송된다
		// PNG파일을 불러올 때, IMAGE_PNG_VALUE를 사용하면 정상적으로 이미지를 전송한다
		httpHeaders.add("Content-type", MediaType.IMAGE_PNG_VALUE);
		byte[] bytes = ipfsService.LoadFile(hash);
		System.out.println(Arrays.toString(bytes));
		return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(bytes);
	}
}
