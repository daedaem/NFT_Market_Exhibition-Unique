package com.ssafy.unique.api.service;

import com.ssafy.unique.api.response.TokenRes;

public interface Web3jService {
	TokenRes tokentransfer(Long ssf) throws Exception;
	TokenRes getBalance() throws Exception;
}
