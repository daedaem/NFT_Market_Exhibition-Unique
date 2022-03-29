package com.ssafy.unique.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.unique.db.entity.Nft;

@Repository
public interface NftRepository extends JpaRepository<Nft, Long> {
	Nft findByNftMetadataUri(String nftMetadataUri);
	
	@Modifying(clearAutomatically = true)
	@Transactional
    @Query(value = " update nft	set nft_token_id = ?, nft_contract_address = ?	where nft_owner_address = ? and nft_metadata_uri = ? ", nativeQuery = true)
	Integer updateNftByNftTokenIdAndNftOwnerAddress(Long nftTokenId, String nftContractAddress, String nftOwnerAddress, String nftMetadataUri);
}
