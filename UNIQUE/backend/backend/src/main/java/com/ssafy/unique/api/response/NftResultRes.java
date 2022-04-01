package com.ssafy.unique.api.response;

import java.util.List;

import com.ssafy.unique.db.entity.Nft;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NftResultRes {
	private int result;
	private Nft nft;
	private List<Nft> nftList;
}
