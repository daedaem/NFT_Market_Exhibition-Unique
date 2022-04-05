package com.ssafy.unique.api.controller;


import com.ssafy.unique.ERC20;
import com.ssafy.unique.api.config.Web3jConfig;
import com.ssafy.unique.api.request.NftReq;
import com.ssafy.unique.api.response.NftDetailResultRes;
import com.ssafy.unique.api.response.NftResultRes;
import com.ssafy.unique.api.service.NftService;
import com.ssafy.unique.api.service.Web3jService;
import com.ssafy.unique.api.service.Web3jServiceImpl;
import com.ssafy.unique.db.entity.Nft;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(
        origins = { "http://localhost:5500", "http://172.30.1.59:5500", "http://192.168.0.100:5500", "http://192.168.0.40:5500","https://j6e205.p.ssafy.io" },
        allowCredentials = "true", // axios가 sessionId를 계속 다른것을 보내는데, 이것을 고정시켜준다
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS })

@RestController
@RequestMapping(value="/nft")
@Tag(name = "nft Controller", description = "NFT 관련 DB를 다룬다.")
public class NftController {

    String ERC20_CONTRACT_ADDRESS = "0x6C927304104cdaa5a8b3691E0ADE8a3ded41a333";
    String USER_ADDRESS = "0x585B365a684C69Dd4d475e444CbD73c6F9a1997e";
    String USER_PRIVATE_KEY = "0x03dc588c73e2a37031c7b58e8af342ec67fbd70d34833a9cde10780c5c9cdd30"; // 사용자의 개인키
    long TX_END_CHECK_DURATION = 3000;
    int TX_END_CHECK_RETRY = 3;

    private final NftService nftService;
    private final Web3jService web3jService;



    public NftController(NftService nftService,Web3jService web3jService) {
        this.nftService = nftService;
        this.web3jService = web3jService;
    }
    private static final int SUCCESS = 1;
    @GetMapping("/items")
    ResponseEntity<NftResultRes> NftList() {
        NftResultRes res = nftService.nftList();

        if(res != null) {
            return new ResponseEntity<NftResultRes>(res, HttpStatus.OK);
        }else {
            return new ResponseEntity<NftResultRes>(res,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/items/{address}")
    ResponseEntity<NftResultRes> NftListByAddress(@PathVariable String address) {
        NftResultRes res = nftService.nftListByNftOwnerAddress(address);
        if(res != null) {
            return new ResponseEntity<NftResultRes>(res,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<NftResultRes>(res,HttpStatus.NOT_FOUND);
        }
    }
    // 궁금증 : TokenId로 NFT 조회하는 기능을 쓸 필요가 있나...?
    @GetMapping("/detail/token/{tokenId}")
    ResponseEntity<NftResultRes> NftListByTokenId(@PathVariable Long tokenId) {
        NftResultRes res = nftService.findAllByNftTokenId(tokenId);
        if(res != null) {
            return new ResponseEntity<NftResultRes>(res,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<NftResultRes>(res,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/detail/{nftSeq}")
    ResponseEntity<NftDetailResultRes> nftDetailByNftSeq(@PathVariable Long nftSeq) {
        NftDetailResultRes res = nftService.findDetailByNftSeq(nftSeq);

        if(res.getResult() == SUCCESS) {
                    return new ResponseEntity<NftDetailResultRes>(res, HttpStatus.OK);
                } else {
                    return new ResponseEntity<NftDetailResultRes>(res,HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping("/web")
    BigInteger setWeb3j() throws Exception {
       BigInteger s = web3jService.test();
//       System.out.println(s);
        return s;
    }





}
