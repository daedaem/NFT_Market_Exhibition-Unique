package com.ssafy.unique.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.unique.api.request.MarketParamReq;
import com.ssafy.unique.api.response.MarketResultRes;
import com.ssafy.unique.api.service.IPFSService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin(
		origins = { "http://localhost:5500", "http://172.30.1.59:5500", "http://192.168.0.100:5500", "http://192.168.0.40:5500","https://j6e205.p.ssafy.io" },
		allowCredentials = "true", // axios가 sessionId를 계속 다른것을 보내는데, 이것을 고정시켜준다
		allowedHeaders = "*",
		methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS })
@RestController
@RequestMapping(value="/market")
public class MarketController {
	
	private final IPFSService ipfsService;
	
	public MarketController(IPFSService _ipfsService) {
		this.ipfsService = _ipfsService;
	}
	
	private static final int SUCCESS = 1;
	
	
	@Operation(description = "마켓에 있는 상품 전체조회")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "성공"),
			@ApiResponse(responseCode = "401",description = "회원 정보가 등록되어 있지 않아 사용 불가능. 로그인 후 사용"),
			@ApiResponse(responseCode = "500",description = "실패"),
	})
	@GetMapping(value="")
	public ResponseEntity<MarketResultRes> marketSearch(MarketParamReq marketParamReq) {
		MarketResultRes res = ;
		
		if (res.getResult() == SUCCESS) {
			return new ResponseEntity<MarketResultRes>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<MarketResultRes>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
