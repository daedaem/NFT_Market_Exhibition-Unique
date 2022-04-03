package com.ssafy.unique.api.service;

import java.util.ArrayList;
import java.util.List;

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
import com.ssafy.unique.db.entity.Member;
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
		
		Member member = memberRepository.findById(memberSeq).get();
		
		try {
			// Exhibition 테이블 생성
			Exhibition ex = exhibitionRepository.save(Exhibition.builder()
					.exhibitionTitle(exhibitionReq.getTitle())
					.exhibitionType(exhibitionReq.getType())
					.exhibitionDescription(exhibitionReq.getDescription())
					.member(member)
					.build()
					);

			// ExhibitionWork 테이블 생성
			for (int i = 0; i < exhibitionReq.getNftSeqList().size(); i++) {
				Nft nft = nftRepository.findById(exhibitionReq.getNftSeqList().get(i)).get();
				exhibitionWorkRepository.save(ExhibitionWork.builder()
						.exhibitionId(ex.getExhibitionId())
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
	public ExhibitionResultRes exhibitionTotalSearch(ExhibitionParamReq exhibitionParamReq) {
		
		ExhibitionResultRes res = new ExhibitionResultRes();
		
		String type = exhibitionParamReq.getType().toUpperCase();
		int limit = exhibitionParamReq.getLimit();
		int offset = exhibitionParamReq.getOffset();
		
		try {
			
			// EX를 pagination 적용해서 search
			// type를 사용해서 검색진행 -> if로 전체검색 + 타입검색
			// 전체 갯수도 돌려준다
			// 타입이 전체인 경우
			if (type.equals("ALL")) {
				System.out.println("ALL TYPE");
				res.setExhibitionList(exhibitionRepository.findTypeAllWithLimitOffset(limit, offset));
				res.setCount(exhibitionRepository.countTypeAllExhibition());
			}
			// CUR또는 PRI를 검색하는 경우
			else {
				System.out.println(type + " TYPE");
				res.setExhibitionList(exhibitionRepository.findTypeOtherWithLimitOffset(type, limit, offset));
				res.setCount(exhibitionRepository.countTypeOtherExhibition(type));
			}
			
			
			// for 루프를 통해서 각 EX의 연관된 가장 첫 NFT 검색
			// 하나의 EX를 꺼내서 그 안의 EXWORK를 꺼내서 그 NFT를 구한다
			List<Nft> nftList = new ArrayList<>();
			for (int i = 0; i < res.getExhibitionList().size(); i++) {
				Exhibition ex = res.getExhibitionList().get(i);
				Long nftSeq = exhibitionWorkRepository.findNftSeqByExhibitionId(ex.getExhibitionId());
				nftList.add(nftRepository.findById(nftSeq).get());
			}
			
			res.setNftList(nftList);
			res.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			res.setResult(FAIL);
		}
		
		return res;
	}

	@Override
	public ExhibitionRes exhibitionDetailSearch(Long exhibitionId) {
		// exId를 받아서 해당 ex와 연관된 모든 exWork 출력
		// exWork에는 nft와 그 nft를 작성한 사람이 있을 것
		// 우선 ExId를 통해 ex를 찾아온다
		// 또한, ExId를 통해, exWork 들을 찾아온다
		// 찾아온 각각의 work들은 NFT와 MEMBER 정보가 추가로 필요하다
		// work에는 NFT정보는 들어있음. MEMBER는 NFT를 보고, 추가로 찾아와야 할것
		
		ExhibitionRes res = new ExhibitionRes();
		
		try {
			// exhibitionId를 사용해서 Exhibition 찾기
			Exhibition ex = exhibitionRepository.findById(exhibitionId).get();
			res.setExhibition(ex);
			
			// exhibitionId를 사용해서 ExhibitionWorkList 찾기
			List<ExhibitionWork> exWorkList = exhibitionWorkRepository.findByExhibitionId(exhibitionId);
			res.setExhibitionWorkList(exWorkList);
			
			// ExhibitionWorkList의 각 Work에 대해서 작성자 Member 찾기
			List<Member> memberList = new ArrayList<>();
			for (int i = 0; i < exWorkList.size(); i++) {
				memberList.add(memberRepository.findById(exWorkList.get(i).getNft().getNftAuthorSeq()).get());
			}
			res.setMemberList(memberList);
			
			res.setResult(SUCCESS);
		} catch( Exception e ) {
			e.printStackTrace();
			res.setResult(FAIL);
		}
		
		return res;
	}
	
}
