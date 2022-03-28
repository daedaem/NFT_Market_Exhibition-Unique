package com.ssafy.unique.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.unique.db.entity.Nft;

import java.util.List;

@Repository
public interface NftRepository extends JpaRepository<Nft, Long> {
	Nft findByNftMetadataUri(String nftMetadataUri);

	List<Nft> findAll();

	List<Nft> findAllByNftOwnerAddress(String address);
}
