package com.ssafy.unique.api.service;

import java.io.File;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.unique.api.request.MemberReq;
import com.ssafy.unique.api.response.MemberResultRes;
import com.ssafy.unique.db.entity.Member;
import com.ssafy.unique.db.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{

	private final PasswordEncoder passwordEncoder;
	private final MemberRepository memberRepository;
	
	public MemberServiceImpl(PasswordEncoder passwordEncoder, MemberRepository memberRepository) {
		this.passwordEncoder = passwordEncoder;
		this.memberRepository = memberRepository;
	}
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	
	String uploadFolder = "upload";
	String uploadPath = "/usr" + File.separator + "share" + File.separator + "nginx" + File.separator + "html";
	
	@SuppressWarnings("finally")
	@Override
	@Transactional
	public MemberResultRes memberRegister(MemberReq memberReq, MultipartHttpServletRequest request) {
		MemberResultRes memberResultRes = new MemberResultRes();
		memberReq.setMemberPassword(passwordEncoder.encode(memberReq.getMemberPassword()));
		
		try {
			// member table 추가
			memberRepository.save(Member.builder()
					.memberId(memberReq.getMemberId())
					.memberPassword(memberReq.getMemberPassword())
					.build()
			);
			
			
			memberResultRes.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			memberResultRes.setResult(FAIL);
		} finally {
			return memberResultRes;
		}

	}

}
