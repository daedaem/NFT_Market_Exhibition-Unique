package com.ssafy.unique.api.request;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NftReq {
	
	private Long nftAuthorSeq;
	
	@NotNull
	private String nftName;
	
	private String nftType;
	
	private String nftWorkUri;
	
	private String nftAuthorName;
	
	private String nftDescription;
}
