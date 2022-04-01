package com.ssafy.unique.api.response;

import java.util.List;

import com.ssafy.unique.db.entity.Exhibition;
import com.ssafy.unique.db.entity.Member;
import com.ssafy.unique.db.entity.Nft;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExhibitionResultRes {
	private int result;
	private int count;
	
	private List<Exhibition> exhibitionList;
	private List<Nft> nftList;
	private List<Member> memberList;
}
