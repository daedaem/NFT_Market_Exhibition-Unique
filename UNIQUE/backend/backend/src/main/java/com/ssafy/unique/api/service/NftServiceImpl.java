package com.ssafy.unique.api.service;

import com.ssafy.unique.api.response.NftResultRes;
import com.ssafy.unique.db.entity.Nft;
import com.ssafy.unique.db.repository.NftRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NftServiceImpl implements NftService{

    private final NftRepository nftRepository;

    public NftServiceImpl(NftRepository nftRepository) {
        this.nftRepository = nftRepository;
    }

    private final int SUCCESS = 1;
    private final int FAIL = -1;

    @Override
    public List<Nft> nftList() { // 조건 불문하고 모든 NFT를 반환!
        List<Nft> list = nftRepository.findAll();
        return list;
    }

    @Override
    public List<Nft> nftListByNftOwnerAddress(String address) {
        List<Nft> list = nftRepository.findAllByNftOwnerAddress(address);
        return list;
    }

    @Override
    public List<Nft> findAllByNftTokenId(Long nftTokenId) {
        List<Nft> list = nftRepository.findAllByNftTokenId(nftTokenId);
        return list;
    }

    @Override
    public NftResultRes findByNftSeq(Long nftSeq) {
        NftResultRes res = new NftResultRes();
        try {
            Nft nft = nftRepository.findByNftSeq(nftSeq);
            if(nft == null) {
                throw new Exception();
            }
            res.setNft(nft);
            res.setResult(SUCCESS);
        } catch(Exception e) {
            e.printStackTrace();
            res.setResult(FAIL);
        }
        return res;
    }

    //토큰 ID로

}
