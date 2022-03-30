package com.ssafy.unique.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.unique.db.entity.ProfileImage;

@Repository
public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = " update profile_image set profile_image_name = ?, profile_image_size = ?, profile_image_url = ?, reg_dt = now() where member_seq = ?", nativeQuery = true)
	Integer updateProfileImageByMemberSeq(String profileImageName, Long profileImageSize, String profileImageUrl, Long memberSeq);
}
