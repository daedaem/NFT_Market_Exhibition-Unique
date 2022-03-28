package com.ssafy.unique.api.service;

import com.ssafy.unique.api.request.NftReq;
import com.ssafy.unique.db.entity.Nft;

import java.util.List;

public interface NftService {

    //NftSeq, nft
    List<Nft> nftList();


    List<Nft> nftListByNftOwnerAddress(String address);

    List<Nft> findAllByNftTokenId(Long nftTokenId);
}
