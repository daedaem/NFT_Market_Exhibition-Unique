package com.ssafy.unique.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NftUpdateReq {
	private Long tokenId;
	private String ownerAddress;
	private String metadataUri;
}
