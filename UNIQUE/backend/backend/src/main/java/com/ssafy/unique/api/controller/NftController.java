package com.ssafy.unique.api.controller;


import com.ssafy.unique.api.request.NftReq;
import com.ssafy.unique.api.service.NftService;
import com.ssafy.unique.db.entity.Nft;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.nashorn.internal.runtime.RewriteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/items")
    ResponseEntity<List<Nft>> NftList() {
        List<Nft> list = nftService.nftList();

        if(list != null) {
            return new ResponseEntity<List<Nft>>(list, HttpStatus.OK);
        }else {
            return new ResponseEntity<List<Nft>>(list,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/items/{address}")
    ResponseEntity<List<Nft>> NftListByAddress(@PathVariable String address) {
        List<Nft> list = nftService.nftListByNftOwnerAddress(address);
        if(list != null) {
            return new ResponseEntity<List<Nft>>(list,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<List<Nft>>(list,HttpStatus.NOT_FOUND);
        }
    }

}
