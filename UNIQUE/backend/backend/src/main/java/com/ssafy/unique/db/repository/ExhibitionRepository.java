package com.ssafy.unique.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.unique.db.entity.Exhibition;

@Repository
public interface ExhibitionRepository extends JpaRepository<Exhibition, Long>{

	@Query( value = " select * from exhibition order by reg_dt desc limit ? offset ? ", nativeQuery = true)
	List<Exhibition> findTypeAllWithLimitOffset(int limit, int offset);
	
	@Query( value = " select count(*) from exhibition ", nativeQuery = true)
	Integer countTypeAllExhibition();
	
	
	
	@Query( value = " select * from exhibition where exhibition_type = ? order by reg_dt desc limit ? offset ? ", nativeQuery = true)
	List<Exhibition> findTypeOtherWithLimitOffset(String type, int limit, int offset);
	
	@Query( value = " select count(*) from exhibition where exhibition_type = ? ", nativeQuery = true)
	Integer countTypeOtherExhibition(String type);
}
