package com.ssafy.unique.api.request;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NftReq {
	
	private Long nftCreatorSeq;
	
	@NotNull
	private String nftName;
	
	private String nftType;
	
	private String nftWorkUri;
}
