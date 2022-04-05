package com.ssafy.unique.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.unique.db.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByMemberId(String memberId);
	
	@Query(value = " select member_address from member where member_seq = ? ", nativeQuery = true)
	String findMemberAddressByMemberSeq(Long memberSeq);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = " update member set member_address = ? where member_seq = ?", nativeQuery = true)
	Integer updateMemberAddressByMemberSeq(String memberAddress, Long memberSeq);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = " update member set profile_image_url = ? where member_seq = ?", nativeQuery = true)
	Integer updateProfileImageUrlByMemberSeq(String profileImageUrl, Long memberSeq);
	
	
	@Query(value= " select * from member where member_seq in ( select * from ( select nft_author_seq from nft group by nft_author_seq order by count(*) desc limit 4) as tmp) ", nativeQuery = true)
	List<Member> findPopularAuthor();
	
	@Query(value= " update member set member_bio = ? where member_seq = ? " , nativeQuery = true)
	Integer updateMemberBioById(String memberBio, Long memberSeq);


	//MemberSeq로 member 정보를 가져옴
	Member findByMemberSeq(Long memberSeq);

	@Query(value = "select profile_image_url from member where member_id = ?",nativeQuery = true)
	String findProfileImage(String memberId);

	Member findByMemberAddress(String memberAddress);
}
