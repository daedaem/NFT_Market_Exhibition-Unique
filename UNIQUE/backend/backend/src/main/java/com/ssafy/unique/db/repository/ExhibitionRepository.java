package com.ssafy.unique.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.unique.db.entity.Exhibition;

@Repository
public interface ExhibitionRepository extends JpaRepository<Exhibition, Long>{

}
