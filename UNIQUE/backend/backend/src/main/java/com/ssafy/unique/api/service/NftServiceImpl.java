package com.ssafy.unique.api.service;

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


}
