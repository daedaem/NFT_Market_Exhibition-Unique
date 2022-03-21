package com.ssafy.unique.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.unique.db.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByMemberId(String memberId);
	
}
