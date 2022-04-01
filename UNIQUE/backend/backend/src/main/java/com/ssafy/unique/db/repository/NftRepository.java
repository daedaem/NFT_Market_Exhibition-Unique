package com.ssafy.unique.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.unique.db.entity.Nft;

import java.util.List;

@Repository
public interface NftRepository extends JpaRepository<Nft, Long> {
	Nft findByNftMetadataUri(String nftMetadataUri);

	List<Nft> findAll();

	List<Nft> findAllByNftOwnerAddress(String address);

	Nft findAllByNftTokenId(Long tokenId);

	Nft findByNftSeq(Long nftSeq);

	@Modifying(clearAutomatically = true)
	@Transactional
    @Query(value = " update nft	set nft_token_id = ?, nft_contract_address = ?	where nft_owner_address = ? and nft_metadata_uri = ? ", nativeQuery = true)
	Integer updateNftByNftTokenIdAndNftOwnerAddress(Long nftTokenId, String nftContractAddress, String nftOwnerAddress, String nftMetadataUri);
	
	@Modifying(clearAutomatically = true)
	@Transactional
    @Query(value = " update nft	set nft_owner_seq = ?, nft_owner_address = ?	where nft_seq = ? ", nativeQuery = true)
	Integer updateOwnerSeqAndOwnerAddressById(Long nftOwnerSeq, String nftOwnerAddress, Long nftSeq);

	@Query(value = " select * from nft where nft_author_seq = ? order by nft_seq desc limit 1 ", nativeQuery = true)
	Nft findRecentWorkById(Long memberSeq);
}
