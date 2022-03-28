package com.ssafy.unique.api.response;

<<<<<<< HEAD
import com.ssafy.unique.api.request.NftReq;

import java.util.List;

public class NftRes {

    private Long nft_req;
    private Long nft_author_seq;
=======
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NftRes {
	private int result;
	private Long nftSeq;
	private String nftMetadataUri;
>>>>>>> a0491097dce360acc914706ec6b79fcbc2de4a93
}
