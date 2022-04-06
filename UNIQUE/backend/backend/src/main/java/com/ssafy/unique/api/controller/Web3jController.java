package com.ssafy.unique.api.controller;

import java.math.BigInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.unique.api.response.TokenRes;
import com.ssafy.unique.api.service.Web3jService;

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
@RequestMapping(value="/web3j")
@Tag(name = "Web3j Controller", description = "ERC20 관련 데이터를 다룬다.")
public class Web3jController {

    private final Web3jService web3jService;

    public Web3jController(Web3jService web3jService) {
        this.web3jService = web3jService;
    }

	private static final int SUCCESS = 1;
	
	
	@Operation(description = "현재 로그인한 유저의 지갑으로 토큰 발급")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(responseCode = "500", description = "실패")
	})
    @GetMapping("/transfer/{ssf}")
    public ResponseEntity<TokenRes> transfer(@PathVariable("ssf") Long ssf) throws Exception {
        System.out.println("Enter transfer()");
    	TokenRes res = web3jService.tokentransfer(ssf);
        
    	if (res.getResult() == SUCCESS) {
    		System.out.println("GET BALANCE");
    		res = web3jService.getBalance();
    	} else {
    		System.out.println("ERROR: TRANSFER FAIL");
    		return new ResponseEntity<TokenRes>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
        
        
		if (res.getResult() == SUCCESS) {
			return new ResponseEntity<TokenRes>(res, HttpStatus.OK);
		} else {
			System.out.println("ERROR: GET BALANCE FAIL");
			return new ResponseEntity<TokenRes>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	
	@Operation(description = "현재 로그인한 유저의 지갑 잔고 확인")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(responseCode = "500", description = "실패")
	})
    @GetMapping("/getbalance")
    public ResponseEntity<TokenRes> getBalance() throws Exception {
    	System.out.println("Enter getBalance()");
    	TokenRes res = web3jService.getBalance();
    	
		if (res.getResult() == SUCCESS) {
			return new ResponseEntity<TokenRes>(res, HttpStatus.OK);
		} else {
			System.out.println("ERROR: GET BALANCE FAIL");
			return new ResponseEntity<TokenRes>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
