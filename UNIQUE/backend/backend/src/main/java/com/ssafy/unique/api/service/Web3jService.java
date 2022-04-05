package com.ssafy.unique.api.service;

import java.io.IOException;
import java.math.BigInteger;

public interface Web3jService {

    BigInteger getBalance() throws Exception;

    void tokentransfer() throws Exception;
}
