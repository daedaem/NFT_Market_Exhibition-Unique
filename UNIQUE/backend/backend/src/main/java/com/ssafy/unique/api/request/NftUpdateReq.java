package com.ssafy.unique.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NftUpdateReq {
	private Long tokenId;
	private String ownerAddress;
	private String metadataUri;
}
