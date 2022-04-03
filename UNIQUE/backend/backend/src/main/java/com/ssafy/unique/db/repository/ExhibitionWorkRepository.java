package com.ssafy.unique.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.unique.db.entity.ExhibitionWork;

@Repository
public interface ExhibitionWorkRepository extends JpaRepository<ExhibitionWork, Long>{

	// 대표작품 1개만 찾아오는 기능
	@Query(value = " select nft_seq from exhibition_work where exhibition_id = ? limit 1 ", nativeQuery = true)
	Long findNftSeqByExhibitionId(Long exhibitionId);
	
	@Query(value = " select * from exhibition_work where exhibition_id = ? ", nativeQuery = true )
	List<ExhibitionWork> findByExhibitionId(Long exhibitionId);
}
