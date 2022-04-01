package com.ssafy.unique.api.response;

import java.util.List;

import com.ssafy.unique.db.entity.Member;
import com.ssafy.unique.db.entity.Nft;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PopularRes {
	private int result;
	
	private List<Member> memberList;
	private List<Nft> nftList;
}
