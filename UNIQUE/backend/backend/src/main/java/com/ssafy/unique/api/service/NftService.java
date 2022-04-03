package com.ssafy.unique.api.service;

import com.ssafy.unique.api.request.NftReq;
import com.ssafy.unique.api.response.NftDetailResultRes;
import com.ssafy.unique.api.response.NftResultRes;
import com.ssafy.unique.db.entity.Nft;

import java.util.List;

public interface NftService {

    //NftSeq, nft
    NftResultRes nftList();


    NftResultRes nftListByNftOwnerAddress(String address);

    NftResultRes findAllByNftTokenId(Long nftTokenId);

    NftResultRes findByNftSeq(Long nftSeq);

    NftDetailResultRes findDetailByNftSeq(Long nftSeq);
}
