package com.ssafy.unique.api.request;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginReq {
	
	@NotNull
	private String memberId;
	
	@NotNull
	private String memberPassword;
}
