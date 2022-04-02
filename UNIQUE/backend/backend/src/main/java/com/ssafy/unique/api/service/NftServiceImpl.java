package com.ssafy.unique.api.service;

import com.ssafy.unique.api.response.NftDetailResultRes;
import com.ssafy.unique.api.response.NftResultRes;
import com.ssafy.unique.db.entity.Market;
import com.ssafy.unique.db.entity.Member;
import com.ssafy.unique.db.entity.Nft;
import com.ssafy.unique.db.repository.MarketRepository;
import com.ssafy.unique.db.repository.MemberRepository;
import com.ssafy.unique.db.repository.NftRepository;
import jnr.a64asm.Mem;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NftServiceImpl implements NftService{

    private final NftRepository nftRepository;
    private final MemberRepository memberRepository;
    private final MarketRepository marketRepository;

    public NftServiceImpl(NftRepository nftRepository, MemberRepository memberRepository, MarketRepository marketRepository) {
        this.nftRepository = nftRepository;
        this.memberRepository = memberRepository;
        this.marketRepository = marketRepository;
    }

    private final int SUCCESS = 1;
    private final int FAIL = -1;

    @Override
    public NftResultRes nftList() { // 조건 불문하고 모든 NFT를 반환!
        NftResultRes res = new NftResultRes();

        try{
            List<Nft> list = nftRepository.findAll();

            if(list == null) {
                throw new Exception();
            }
            res.setNftList(list);
            res.setResult(SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setResult(FAIL);
        }
        return res;
    }

    @Override
    public NftResultRes nftListByNftOwnerAddress(String address) {
        NftResultRes res = new NftResultRes();
        try {
            List<Nft> list = nftRepository.findAllByNftOwnerAddress(address);
            if(list == null) {
                throw new Exception();
            }
            res.setNftList(list);
            res.setResult(SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setResult(FAIL);
        }
        return res;
    }

    @Override
    public NftResultRes findAllByNftTokenId(Long nftTokenId) {
        NftResultRes res = new NftResultRes();
        try {
            Nft nft = nftRepository.findAllByNftTokenId(nftTokenId);
            if(nft == null) {
                throw new Exception();
            }
            res.setNft(nft);
            res.setResult(SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setResult(FAIL);
        }

        return res;
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

    @Override
    public NftDetailResultRes findDetailByNftSeq(Long nftSeq) {
        /*
        nft_seq값을 받아서 다음과 같은 정보를 호출한다
        - nft 테이블 관련 전체 정보
        - nft 소유자 정보
        - nft 거래 내역
        - nft Author 정보
         */
        NftDetailResultRes res = new NftDetailResultRes();
        try {
            Nft nft = nftRepository.findByNftSeq(nftSeq); // nft 테이블 관련 전체 정보
            Member Owner = memberRepository.findByMemberSeq(nft.getNftOwnerSeq()); // nft 판매자 정보
            Member Author = memberRepository.findByMemberSeq(nft.getNftAuthorSeq()); // nft Author 정보
            Market market = marketRepository.findCurrentMarketByNftSeq(nft.getNftSeq()); // nft 거래 내역

            res.setNft(nft);
            res.setOwnerMember(Owner);
            res.setAuthorMember(Author);
            res.setMarket(market);
            res.setResult(SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setResult(FAIL);
        }
        return res;
    }

    //토큰 ID로

}
