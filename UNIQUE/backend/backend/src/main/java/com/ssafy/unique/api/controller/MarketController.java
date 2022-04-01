package com.ssafy.unique.api.controller;

import com.ssafy.unique.api.response.NftResultRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.unique.api.request.MarketParamReq;
import com.ssafy.unique.api.request.MarketReq;
import com.ssafy.unique.api.request.MarketUpdateReq;
import com.ssafy.unique.api.response.MarketResultRes;
import com.ssafy.unique.api.response.ResultRes;
import com.ssafy.unique.api.service.IPFSService;
import com.ssafy.unique.api.service.MarketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin(origins = { "http://localhost:5500", "http://172.30.1.59:5500", "http://192.168.0.100:5500",
		"http://192.168.0.40:5500", "https://j6e205.p.ssafy.io" }, allowCredentials = "true", // axios가 sessionId를 계속
																								// 다른것을 보내는데, 이것을 고정시켜준다
		allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS })
@RestController
@RequestMapping(value = "/market")
public class MarketController {

	private final IPFSService ipfsService;
	private final MarketService marketService;
	
	public MarketController(IPFSService _ipfsService, MarketService _marketService) {
		this.ipfsService = _ipfsService;
		this.marketService = _marketService;
	}

	private static final int SUCCESS = 1;
	
	
	
	@Operation(description = "마켓에 있는 상품 전체조회")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "성공"),
			@ApiResponse(responseCode = "401",description = "회원 정보가 등록되어 있지 않아 사용 불가능. 로그인 후 사용"),
			@ApiResponse(responseCode = "500",description = "실패"),
	})
	@GetMapping(value="")
	// GetMapping이지만, PostMan에서 확인하기 위해서 @RequestBody 사용
	public ResponseEntity<MarketResultRes> marketTotalSearch(MarketParamReq marketParamReq) {
		MarketResultRes res = marketService.marketTotalSearch(marketParamReq);
		
		if (res.getResult() == SUCCESS) {
			return new ResponseEntity<MarketResultRes>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<MarketResultRes>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@Operation(description = "마켓에 있는 상품 상세조회")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "성공"),
			@ApiResponse(responseCode = "401",description = "회원 정보가 등록되어 있지 않아 사용 불가능. 로그인 후 사용"),
			@ApiResponse(responseCode = "500",description = "실패"),
	})
	@GetMapping(value="/detail/{marketId}")
	public ResponseEntity<MarketResultRes> marketDetailSearch(@PathVariable("marketId") Long marketId) {
		
		MarketResultRes res = marketService.marketDetailSearch(marketId);
		
		if (res.getResult() == SUCCESS) {
			return new ResponseEntity<MarketResultRes>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<MarketResultRes>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@Operation(description = "마켓 상품등록")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "성공"),
			@ApiResponse(responseCode = "401",description = "회원 정보가 등록되어 있지 않아 사용 불가능. 로그인 후 사용"),
			@ApiResponse(responseCode = "500",description = "실패"),
	})
	@PostMapping(value="/register")
	public ResponseEntity<ResultRes> marketRegister(@RequestBody MarketReq marketReq) {
		System.out.println(marketReq);
		ResultRes res = marketService.marketRegister(marketReq);
		
		if (res.getResult() == SUCCESS) {
			return new ResponseEntity<ResultRes>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<ResultRes>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@Operation(description = "마켓 상품구매")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "성공"),
			@ApiResponse(responseCode = "401",description = "회원 정보가 등록되어 있지 않아 사용 불가능. 로그인 후 사용"),
			@ApiResponse(responseCode = "500",description = "실패"),
	})
	@PutMapping(value="/purchase")
	public ResponseEntity<ResultRes> marketPurchase(@RequestBody MarketUpdateReq marketUpdateReq) {
		
		ResultRes res = marketService.marketPurchase(marketUpdateReq);
		
		if (res.getResult() == SUCCESS) {
			return new ResponseEntity<ResultRes>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<ResultRes>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@Operation(description = "마켓 상품등록 취소")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "성공"),
			@ApiResponse(responseCode = "401",description = "회원 정보가 등록되어 있지 않아 사용 불가능. 로그인 후 사용"),
			@ApiResponse(responseCode = "500",description = "실패"),
	})
	@PutMapping(value="/cancel")
	public ResponseEntity<ResultRes> marketCancel(@RequestBody MarketUpdateReq marketUpdateReq) {
		
		ResultRes res = marketService.marketCancel(marketUpdateReq);
		
		if (res.getResult() == SUCCESS) {
			return new ResponseEntity<ResultRes>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<ResultRes>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@GetMapping(value = "/record/{nftSeq}")
	public ResponseEntity<MarketResultRes> marketNftTradeRecord(@PathVariable Long nftSeq) {

		MarketResultRes res = marketService.marketNftTradeRecord(nftSeq);
//		MarketResultRes res_2 = marketService.marketNftCurrentTrade(nftSeq);

		if(res.getResult() == SUCCESS) {
			return new ResponseEntity<MarketResultRes>(res, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<MarketResultRes>(res, HttpStatus.NOT_FOUND);
		}
	}


}
