package com.ssafy.unique.api.service;

import javax.transaction.Transactional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ssafy.unique.api.request.ExhibitionParamReq;
import com.ssafy.unique.api.request.ExhibitionReq;
import com.ssafy.unique.api.response.ExhibitionRes;
import com.ssafy.unique.api.response.ExhibitionResultRes;
import com.ssafy.unique.api.response.ResultRes;
import com.ssafy.unique.db.entity.Exhibition;
import com.ssafy.unique.db.entity.ExhibitionWork;
import com.ssafy.unique.db.entity.Nft;
import com.ssafy.unique.db.repository.ExhibitionRepository;
import com.ssafy.unique.db.repository.ExhibitionWorkRepository;
import com.ssafy.unique.db.repository.MemberRepository;
import com.ssafy.unique.db.repository.NftRepository;

@Service
public class ExhibitionServiceImpl implements ExhibitionService {

	private final MemberRepository memberRepository;
	private final NftRepository nftRepository;
	private final ExhibitionRepository exhibitionRepository;
	private final ExhibitionWorkRepository exhibitionWorkRepository;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	public ExhibitionServiceImpl(MemberRepository _memberRepository, 
			NftRepository _nftRepository, 
			ExhibitionRepository _exhibitionRepository, 
			ExhibitionWorkRepository _exhibitionWorkRepository) {
		this.memberRepository = _memberRepository;
		this.nftRepository = _nftRepository;
		this.exhibitionRepository = _exhibitionRepository;
		this.exhibitionWorkRepository = _exhibitionWorkRepository;
	}
	
	@Override
	@Transactional
	public ResultRes exhibitionRegister(ExhibitionReq exhibitionReq) {
		
		ResultRes res = new ResultRes();
		
		// Security Context에서 memberSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Long memberSeq = Long.parseLong(authentication.getName());
		
		try {
			// Exhibition 테이블 생성
			exhibitionRepository.save(Exhibition.builder()
					.exhibitionTitle(exhibitionReq.getTitle())
					.exhibitionType(exhibitionReq.getType())
					.exhibitionDescription(exhibitionReq.getDescription())
					.memberSeq(memberSeq)
					.build()
					);

			// ExhibitionWork 테이블 생성
			for (int i = 0; i < exhibitionReq.getNftSeqList().size(); i++) {
				Nft nft = nftRepository.findById(exhibitionReq.getNftSeqList().get(i)).get();
				exhibitionWorkRepository.save(ExhibitionWork.builder()
						.nft(nft)
						.build()
						);
			}
			
			res.setResult(SUCCESS);
		} catch( Exception e ) {
			e.printStackTrace();
			res.setResult(FAIL);
		}
		
		return res;
	}

	@Override
	@Transactional
	public ExhibitionResultRes exhibitionTotalSearch(ExhibitionParamReq exhibitionParamReq) {
		ExhibitionResultRes res = new ExhibitionResultRes();
		
		try {
			
			// EX를 pagination 적용해서 search
			// 각 EX와 연관된 가장 첫 NFT 검색
			// 각 EX를 작성한 사람 검색
			
			res.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			res.setResult(FAIL);
		}
		
		return res;
	}

	@Override
	public ExhibitionRes exhibitionDetailSearch(Long exhibitionId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
