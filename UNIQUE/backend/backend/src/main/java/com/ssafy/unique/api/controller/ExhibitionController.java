package com.ssafy.unique.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.unique.api.request.ExhibitionParamReq;
import com.ssafy.unique.api.request.ExhibitionReq;
import com.ssafy.unique.api.response.ExhibitionRes;
import com.ssafy.unique.api.response.ExhibitionResultRes;
import com.ssafy.unique.api.response.ResultRes;
import com.ssafy.unique.api.service.ExhibitionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(
		origins = { "http://localhost:5500", "http://172.30.1.59:5500", "http://192.168.0.100:5500", "http://192.168.0.40:5500","https://j6e205.p.ssafy.io" },
		allowCredentials = "true", // axios가 sessionId를 계속 다른것을 보내는데, 이것을 고정시켜준다
		allowedHeaders = "*",
		methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS })

@RestController
@RequestMapping(value="/exhibition")
@Tag(name = "Exhibition Controller", description = "전시회와 관련된 API를 조작한다 (전시회 생성, 전시회 목록 조회, 전시회 상세 조회)")
public class ExhibitionController {
	
	private final ExhibitionService exhibitionService;
	
	public ExhibitionController(ExhibitionService _exhibitionService) {
		this.exhibitionService = _exhibitionService;
	}
	
	private static final int SUCCESS = 1;
	
	@Operation(description = "전시회 등록")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(responseCode = "500", description = "실패")
	})
	@PostMapping("/register")
	public ResponseEntity<ResultRes> exhibitionRegister(@RequestBody ExhibitionReq exhibitionReq ) {
		System.out.println(exhibitionReq);
		ResultRes res = exhibitionService.exhibitionRegister(exhibitionReq);
		
		if (res.getResult() == SUCCESS) {
			return new ResponseEntity<ResultRes>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<ResultRes>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(description = "전시회 전체 조회")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(responseCode = "500", description = "실패")
	})
	@GetMapping("") 
	public ResponseEntity<ExhibitionResultRes> exhibitionTotalSearch(ExhibitionParamReq exhibitionParamReq) {
		System.out.println(exhibitionParamReq);
		ExhibitionResultRes res = exhibitionService.exhibitionTotalSearch(exhibitionParamReq);
		
		if (res.getResult() == SUCCESS) {
			return new ResponseEntity<ExhibitionResultRes>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<ExhibitionResultRes>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(description = "전시회 상세 조 회")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(responseCode = "500", description = "실패")
	})
	@GetMapping("/detail/{exhibitionId}")
	public ResponseEntity<ExhibitionRes> exhibitionDetailSearch(@PathVariable("exhibitionId") Long exhibitionId) {
		System.out.println(exhibitionId);
		ExhibitionRes res = exhibitionService.exhibitionDetailSearch(exhibitionId);
		
		if (res.getResult() == SUCCESS) {
			return new ResponseEntity<ExhibitionRes>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<ExhibitionRes>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
