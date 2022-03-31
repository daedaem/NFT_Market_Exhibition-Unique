package com.ssafy.unique.api.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.unique.api.request.MemberReq;
import com.ssafy.unique.api.response.MemberResultRes;
import com.ssafy.unique.api.response.ResultRes;

public interface MemberService {
	public MemberResultRes memberRegister(MemberReq memberReq, MultipartHttpServletRequest request);
	public ResultRes memberWalletRegister(String wallet);
	public ResultRes profileImageUpdate( MultipartHttpServletRequest request );
	
//	public MemberResultRes memberMypageSearch();
	
//	public MemberResultRes passwordCheck(MemberRes memberRes);
//	public MemberResultRes memberInfoUpdate(MemberRes memberRes);
//	public MemberResultRes memberPasswordUpdate(MemberRes memberRes);
	
//	public MemberResultRes memberDelete(int memberSeq);
//	public MemberResultRes memberExist(String memberPhone);
//	public MemberResultRes memberPage(int memberSeq);
	
	// 중복체크
//	public MemberResultRes checkMemberId(String memberId);
//	public MemberResultRes checkMemberEmail(String memberEmail);
//	public MemberResultRes checkMemberPhone(String memberPhone);
	
//	// 아이디/비밀번호 찾기
//	public MemberResultRes memberFindId(String memberEmail);
//	public MemberResultRes memberFindPw(MemberRes memberRes);
}
