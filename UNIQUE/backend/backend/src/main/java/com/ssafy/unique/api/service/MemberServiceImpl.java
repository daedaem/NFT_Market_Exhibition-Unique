package com.ssafy.unique.api.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.unique.api.request.MemberBioReq;
import com.ssafy.unique.api.request.MemberReq;
import com.ssafy.unique.api.response.MemberRes;
import com.ssafy.unique.api.response.MemberResultRes;
import com.ssafy.unique.api.response.PopularRes;
import com.ssafy.unique.api.response.ResultRes;
import com.ssafy.unique.db.entity.Member;
import com.ssafy.unique.db.entity.Nft;
import com.ssafy.unique.db.entity.ProfileImage;
import com.ssafy.unique.db.repository.MemberRepository;
import com.ssafy.unique.db.repository.NftRepository;
import com.ssafy.unique.db.repository.ProfileImageRepository;

@Service
public class MemberServiceImpl implements MemberService{

	private final PasswordEncoder passwordEncoder;
	private final MemberRepository memberRepository;
	private final ProfileImageRepository profileImageRepository;
	private final NftRepository nftRepository;

	public MemberServiceImpl(PasswordEncoder passwordEncoder, MemberRepository memberRepository, ProfileImageRepository profileImageRepository, NftRepository nftRepository) {
		this.passwordEncoder = passwordEncoder;
		this.memberRepository = memberRepository;
		this.profileImageRepository = profileImageRepository;
		this.nftRepository = nftRepository;
	}

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;


	String uploadFolder = "upload";
	//String uploadPath = "C:" + File.separator + "SSAFY" + File.separator + "profileImage";
	String uploadPath = "/usr" + File.separator + "share" + File.separator + "nginx" + File.separator + "html";

	@SuppressWarnings("finally")
	@Override
	@Transactional
	public MemberResultRes memberRegister(MemberReq memberReq, MultipartHttpServletRequest request) {
		MemberResultRes memberResultRes = new MemberResultRes();
		memberReq.setMemberPassword(passwordEncoder.encode(memberReq.getMemberPassword()));

		try {
			// member table 추가
			Member member = memberRepository.save(Member.builder()
					.memberId(memberReq.getMemberId())
					.memberPassword(memberReq.getMemberPassword())
					.memberBio(memberReq.getMemberBio())
					.build()
					);
			System.out.println(member.getMemberSeq());

			// Add Profile Image
			MultipartFile userProfileImage = request.getFile("profileImage");

			if ( userProfileImage != null ) {
				File uploadDir = new File(uploadPath + File.separator + uploadFolder);
				if (!uploadDir.exists()) 
					uploadDir.mkdirs();

				// profile Image Name
				String profileImageName = userProfileImage.getOriginalFilename();
				// Random File Id
				UUID uuid = UUID.randomUUID();
				// file extension
				String extension = FilenameUtils.getExtension(profileImageName);
				// saving file name
				String savingFileName = uuid + "." + extension;

				// file destination
				File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				System.out.println(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				// file save
				userProfileImage.transferTo(destFile);
				

				// insert profile image
				profileImageRepository.save(ProfileImage.builder()
						.memberSeq(member.getMemberSeq())
						.profileImageName(profileImageName)
						.profileImageSize(userProfileImage.getSize())
						.profileImageUrl(uploadFolder + "/" + savingFileName)
//						.profileImageUrl(uploadPath + File.separator + uploadFolder + File.separator + savingFileName)
						.build()
						);
				
				// update profile image url in MEMBER table
				memberRepository.updateProfileImageUrlByMemberSeq(
						uploadFolder + "/" + savingFileName, 
						member.getMemberSeq()
						);
					

			} else {
				// insert empty profile image
				// DB에서 deafult profileImageUrl을 설정해주어야한다
				profileImageRepository.save(ProfileImage.builder()
						.memberSeq(member.getMemberSeq())
						.build()
						);
			}

			memberResultRes.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			memberResultRes.setResult(FAIL);
		} finally {
			return memberResultRes;
		}

	}

	@Override
	public ResultRes memberWalletRegister(String wallet) {
		ResultRes resultRes = new ResultRes();

		// Security Context에서 nftCreatorSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Long memberSeq = Long.parseLong(authentication.getName());

		int result = memberRepository.updateMemberAddressByMemberSeq(wallet, memberSeq);

		if (result == SUCCESS) {
			resultRes.setResult(SUCCESS);
		} else {
			resultRes.setResult(FAIL);
		}

		return resultRes;
	}

	@SuppressWarnings("finally")
	@Override
	public ResultRes profileImageUpdate(MultipartHttpServletRequest request) {
		ResultRes res = new ResultRes();

		// Security Context에서 MEMBER_SEQ를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Long memberSeq = Long.parseLong(authentication.getName());


		// Add Profile Image
		MultipartFile userProfileImage = request.getFile("profileImage");
		
		try {
			// 이미지가 있는 경우
			if ( userProfileImage != null ) {
				File uploadDir = new File(uploadPath + File.separator + uploadFolder);
				if (!uploadDir.exists()) 
					uploadDir.mkdirs();

				// profile Image Name
				String profileImageName = userProfileImage.getOriginalFilename();
				// Random File Id
				UUID uuid = UUID.randomUUID();
				// file extension
				String extension = FilenameUtils.getExtension(profileImageName);
				// saving file name
				String savingFileName = uuid + "." + extension;

				// file destination
				File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				System.out.println(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				// file save
				userProfileImage.transferTo(destFile);

				// update profile image
				profileImageRepository.updateProfileImageByMemberSeq(
						profileImageName, 
						userProfileImage.getSize(), 
						uploadFolder + File.separator + savingFileName,
						memberSeq
						);
				// update member
				memberRepository.updateProfileImageUrlByMemberSeq(
						uploadFolder + File.separator + savingFileName, 
						memberSeq
						);
				
			} 
			// 이미지가 없는 경우
			else {
				// update empty profile image
				profileImageRepository.updateProfileImageByMemberSeq(
						"noImage",
						Long.valueOf(1),
						uploadFolder + File.separator + "noImage.png",
						memberSeq
						);
				// update member
				memberRepository.updateProfileImageUrlByMemberSeq(
						uploadFolder + File.separator + "noImage.png", 
						memberSeq
						);
			}

			res.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			res.setResult(FAIL);
		} finally {
			return res;
		}

	}

	@Override
	public PopularRes popularAuthorSearch() {
		
		PopularRes res = new PopularRes();

		try {
			// 먼저 member의 List를 구한다 -> 4명의 인원을 고르고, 그 순서는 작업물이 가장 많은 순서다
			List<Member> memberList = memberRepository.findPopularAuthor();

			// 각 리스트에 있는 member를 사용해서, 해당 member가 가장 최근에 create한 NFT를 각각 구한다 -> 4개의 NFT가 List 안에 저장될 것
			// for 구문을 사용해서 멤버 리스트 안의 멤버를 각각 조회해서 가장 최근의 작품을 찾는다. desc를 nft_Seq에 사용
			List<Nft> nftList = new ArrayList<>();
			for (int i = 0; i < memberList.size(); i++) {
				nftList.add(nftRepository.findRecentWorkById(memberList.get(i).getMemberSeq()));
			}

			res.setMemberList(memberList);
			res.setNftList(nftList);

			res.setResult(SUCCESS);
		} catch(Exception e) {
			res.setResult(FAIL);
		}
		
		return res;
	}

	@Override
	public ResultRes updateMemberBio(MemberBioReq memberBioReq) {

		ResultRes res = new ResultRes();
		
		// Security Context에서 MEMBER_SEQ를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Long memberSeq = Long.parseLong(authentication.getName());
		
		try {
			memberRepository.updateMemberBioById(memberBioReq.getMemberBio(), memberSeq);
			res.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			res.setResult(FAIL);
		}
		
		return res;
	}

	
	//유저의 이미지 반환
	@Override
	public MemberResultRes findMemberProfileImageById(String memberId) {
		MemberResultRes res = new MemberResultRes();
		try {
			String profileImage = memberRepository.findProfileImage(memberId);
			res.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			res.setResult(FAIL);
		}
		return res;
	}

	@Override
	public MemberRes findMemberById(Long memberSeq) {
		Member member = memberRepository.findById(memberSeq).get();
		
		MemberRes memberRes = MemberRes.builder()
				.memberSeq(member.getMemberSeq())
				.memberId(member.getMemberId())
				.memberAddress(member.getMemberAddress())
				.profileImageUrl(member.getProfileImageUrl())
				.authority(member.isAuthority())
				.memberBio(member.getMemberBio())
				.ssf(member.getSsf())
				.regDt(member.getRegDt())
				.build();
		
		return memberRes;
	}

}
