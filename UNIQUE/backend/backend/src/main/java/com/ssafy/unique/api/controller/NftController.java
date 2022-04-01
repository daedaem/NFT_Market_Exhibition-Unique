package com.ssafy.unique.api.controller;


import com.ssafy.unique.api.request.NftReq;
import com.ssafy.unique.api.response.NftResultRes;
import com.ssafy.unique.api.service.NftService;
import com.ssafy.unique.db.entity.Nft;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

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

    private final NftService nftService;

    public NftController(NftService nftService) {
        this.nftService = nftService;
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

    @GetMapping("/{nftSeq}")
                    ResponseEntity<NftResultRes> nftListByNftSeq(@PathVariable Long nftSeq) {
                NftResultRes res = nftService.findByNftSeq(nftSeq);

        if(res.getResult() == SUCCESS) {
                    return new ResponseEntity<NftResultRes>(res, HttpStatus.OK);
                } else {
                    return new ResponseEntity<NftResultRes>(res,HttpStatus.NOT_FOUND);
        }


    }

}
