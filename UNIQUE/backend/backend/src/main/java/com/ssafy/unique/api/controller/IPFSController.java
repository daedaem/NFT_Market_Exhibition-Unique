package com.ssafy.unique.api.controller;

import java.util.Arrays;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.unique.api.request.NftReq;
import com.ssafy.unique.api.service.IPFSService;

@CrossOrigin(
		origins = { "http://localhost:5500", "http://172.30.1.59:5500", "http://192.168.0.100:5500", "http://192.168.0.40:5500","https://j6e205.p.ssafy.io" },
		allowCredentials = "true", // axios가 sessionId를 계속 다른것을 보내는데, 이것을 고정시켜준다
		allowedHeaders = "*",
		methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS })
@RestController
@Tag(name = "IPFS Controller", description = "IPFS의 파일 업로드, NFT화 등, IPFS에서 NFT 관련 기능을 다룬다.")
public class IPFSController {
	
	@Autowired
	private IPFSService ipfsService;

	@Operation(description = "api 요청 테스트용")
	@GetMapping(value="/hello")
	public String hello() {
		System.out.println("hello");
		return "hello";
	}

	@Operation(description = "go-ipfs에 프론트에서 온 파일을 업로드하는 api")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "업로드 성공 후 해당 데이터를 hash화 하여 DB에 등록"),
			@ApiResponse(responseCode = "401",description = "회원 정보가 등록되어 있지 않아 사용 불가능. 로그인 후 사용")
	})
	@PostMapping(value="/file")
	public String saveFile(
			@ModelAttribute NftReq nftReq, 
			MultipartHttpServletRequest request) {
		System.out.println("Upload 진입");
		return ipfsService.saveFile(nftReq, request);
	}
	@Operation(description = "프론트에서 요청받은 파일의 메타데이터를 반환하는 api")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "DB에 등록된 Hash 값을 활용하여 정상적으로 메타데이터를 반환"),
			@ApiResponse(responseCode = "401",description = "회원 정보가 등록되어 있지 않아 사용 불가능. 로그인 후 사용")
	})
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
