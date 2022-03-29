package com.ssafy.unique.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.unique.api.request.LoginReq;
import com.ssafy.unique.api.request.MemberReq;
import com.ssafy.unique.api.request.WalletRegisterReq;
import com.ssafy.unique.api.response.MemberRes;
import com.ssafy.unique.api.response.MemberResultRes;
import com.ssafy.unique.api.response.ResultRes;
import com.ssafy.unique.api.service.CustomUserDetailsService;
import com.ssafy.unique.api.service.MemberService;
import com.ssafy.unique.jwt.JwtFilter;
import com.ssafy.unique.jwt.TokenProvider;

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
@RequestMapping(value="/members")
@Tag(name = "Member Controller", description = "멤버와 관련된 API를 조작한다(로그인,회원가입)")
public class MemberController {

	private final CustomUserDetailsService customUserDetailsService;
	private final MemberService memberService;
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	public MemberController(CustomUserDetailsService customUserDetailsService, MemberService memberService, TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) { 
		this.customUserDetailsService = customUserDetailsService; 
		this.memberService = memberService;
		this.tokenProvider = tokenProvider;
		this.authenticationManagerBuilder = authenticationManagerBuilder;
	}

	private static final int SUCCESS = 1;
	

	@Operation(description = "로그인 과정을 설명")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "로그인 성공"),
			@ApiResponse(responseCode = "401", description = "유저 정보가 일치하지 않음. \b 다시 시도")
	})
	@PostMapping("/login")
	public ResponseEntity<MemberRes> memberLogin(@RequestBody LoginReq loginReq) {
		System.out.println("Enter memberLogin()");
		// userId, userPassword를 파라미터로 받아서 UsernamePasswordAuthenticationToken을 생성한다
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(loginReq.getMemberId(), loginReq.getMemberPassword());
		System.out.println("authenticationToken : " + authenticationToken);

		// 토큰으로 Authentication 객체를 생성하려고 authentication 메소드가 실행 될 때, loadUserByUsername 메소드가 실행된다
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

		// 결과값으로 생성된 객체를 SecurityContext에 저장한다
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println("authentication : " + authentication);

		// 인증정보를 기준으로 tokenProvider의 createToken을 통해서 JWT토큰을 생성한다
		String jwt = tokenProvider.createToken(authentication);
		System.out.println("jwt : " + jwt);

		// 토큰을 헤더에 넣어준다
		HttpHeaders httpHeaders = new HttpHeaders();
		
		// CORS이슈
		httpHeaders.add("Access-Control-Expose-Headers", "AUTHORIZATION");
		
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
		System.out.println("httpHeaders : " + httpHeaders);

		// 유저 정보를 DB에서 받아온다
		MemberRes memberRes = customUserDetailsService.getMemberInfo(loginReq.getMemberId());
		
		return new ResponseEntity<MemberRes> (memberRes, httpHeaders, HttpStatus.OK);
	}

	@Operation(description = "회원가입 과정을 설명")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "회원가입 성공"),
			@ApiResponse(responseCode = "500", description = "회원 정보에 문제가 있음. ")
	})
	@PostMapping("/register")
	public ResponseEntity<MemberResultRes> memberRegister(@ModelAttribute MemberReq memberReq, MultipartHttpServletRequest request) {
		
		MemberResultRes memberResultRes = memberService.memberRegister(memberReq, request);
		
		if (memberResultRes.getResult() == SUCCESS) {
			return new ResponseEntity<MemberResultRes>(memberResultRes, HttpStatus.OK);
		} else {
			return new ResponseEntity<MemberResultRes>(memberResultRes, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@Operation(description = "지갑을 등록")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "지갑 등록 성공"),
			@ApiResponse(responseCode = "500", description = "지갑 등록 실패")
	})
	@PutMapping("/wallet")
	public ResponseEntity<ResultRes> memberWalletRegister(@RequestBody WalletRegisterReq wallet) {
		System.out.println(wallet.getWallet());
		ResultRes resultRes = memberService.memberWalletRegister(wallet.getWallet());
		
		if (resultRes.getResult() == SUCCESS) {
			return new ResponseEntity<ResultRes> (resultRes, HttpStatus.OK);
		} else {
			return new ResponseEntity<ResultRes> (resultRes, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}













