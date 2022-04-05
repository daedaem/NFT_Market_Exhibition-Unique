package com.ssafy.unique.api.controller;

import com.ssafy.unique.api.service.Web3jService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;


@CrossOrigin(
        origins = { "http://localhost:5500", "http://172.30.1.59:5500", "http://192.168.0.100:5500", "http://192.168.0.40:5500","https://j6e205.p.ssafy.io" },
        allowCredentials = "true", // axios가 sessionId를 계속 다른것을 보내는데, 이것을 고정시켜준다
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS })

@RestController
@RequestMapping(value="/web3j")
@Tag(name = "nft Controller", description = "ERC20 관련 데이터를 다룬다.")
public class Web3jController {

    private final Web3jService web3jService;

    public Web3jController(Web3jService web3jService) {
        this.web3jService = web3jService;
    }


    @GetMapping("/transfer")
    public void transfer() throws Exception {
        web3jService.tokentransfer();
    }

    @GetMapping("/getbalance")
    public BigInteger getBalance() throws Exception {
        return web3jService.getBalance();
    }
}
