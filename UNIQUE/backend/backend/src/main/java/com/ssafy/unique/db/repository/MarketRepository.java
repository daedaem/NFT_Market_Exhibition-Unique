package com.ssafy.unique.db.repository;

import java.util.List;

import com.ssafy.unique.db.entity.Nft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.unique.db.entity.Market;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {
	
	
	@Query(value =   " select * "
					+  " from market "
					+ " where ended = false "
					+   " and canceled = false "
					+ " limit ? "
					+" offset ? ", nativeQuery = true)
	List<Market> findTypeAllWithLimitOffset(int limit, int offset);
	
	@Query(value = " select count(*) from market where ended = false and canceled = false ", nativeQuery = true)
	Integer countTypeAllMarketWork();
	
	
	
	
	
	@Query(value =  " select * "
			       +  " from market "
			       +  " join nft "
			       +    " on market.nft_seq = nft.nft_seq "
			       + " where ended = false "
			       +   " and canceled = false "
			       +   " and nft_name like ?% "
			       + " limit ? "
			       +" offset ? ", nativeQuery = true)
	List<Market> findTypeAllWithLimitOffsetSearchWord(String searchWord, int limit, int offset);
	
	@Query(value =  " select count(*) "
		           +  " from market "
		           +  " join nft "
		           +    " on market.nft_seq = nft.nft_seq "
		           + " where ended = false "
		           +   " and canceled = false "
		           +   " and nft_name like ?% ", nativeQuery = true)
	Integer countTypeAllMarketWorkWithSearchWord(String searchWord);
	
	
	
	
	
	@Query(value =  " select * "
			      +   " from market "
			      +   " join nft "
			      +     " on market.nft_seq = nft.nft_seq "
			      +  " where ended = false "
			      +    " and canceled = false "
			      +    " and nft_type like ?% "
			      +  " limit ? "
			      + " offset ? ", nativeQuery = true)
	List<Market> findTypeOtherWithLimitOffset(String type, int limit, int offset);
	
	@Query(value =  " select count(*) "
		          +   " from market "
		          +   " join nft "
		          +     " on market.nft_seq = nft.nft_seq "
		          +  " where ended = false "
		          +    " and canceled = false "
		          +    " and nft_type like ?% " , nativeQuery = true)	
	Integer countTypeOtherMarketWork(String type);
	
	
	
	
	
	@Query(value =  " select * "
			      +   " from market "
			      +   " join nft "
			      +     " on market.nft_seq = nft.nft_seq "
			      +  " where ended = false "
			      +    " and canceled = false "
			      +    " and nft_type like ?% "
			      +    " and nft_name like ?% "
			      +  " limit ? "
			      + " offset ?  ", nativeQuery = true)
	List<Market> findTypeOtherWithLimitOffsetSearchWord(String type, String searchWord, int limit, int offset);
	
	@Query(value =  " select count(*) "
		      +   " from market "
		      +   " join nft "
		      +     " on market.nft_seq = nft.nft_seq "
		      +  " where ended = false "
		      +    " and canceled = false "
		      +    " and nft_type like ?% "
		      +    " and nft_name like ?% ", nativeQuery = true)
	Integer countTypeOtherMarketWorkWithSearchWord(String type, String searchWord);
	
	
	
	
	@Query(value = " select * from market where nft_seq = ? and ended = true ", nativeQuery = true)
	List<Market> findTransactionHistoryById(Long nft_seq);
	
	
	
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = " update market set buyer = ?, ended = ? where market_id = ?", nativeQuery = true)
	Integer updateBuyerAndEndedById(String buyer, boolean ended, Long marketId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = " update market set ended = ?, canceled = ? where market_id = ?", nativeQuery = true)
	Integer updateEndedAndCanceledById(boolean ended, boolean canceled, Long marketId);


	// Nft의 과거~현재 거래 이력을 확인
	@Query(value = " select * from market where nft_seq = ?", nativeQuery = true)
	List<Market> findRecordByNftSeq(Long nftSeq);

	// NFT의 현재 거래현황만 확인
	@Query(value = "select * from market where nft_seq = ? order by start_time desc limit 1", nativeQuery = true)
	Market findCurrentMarketByNftSeq(Long nftSeq);
}
