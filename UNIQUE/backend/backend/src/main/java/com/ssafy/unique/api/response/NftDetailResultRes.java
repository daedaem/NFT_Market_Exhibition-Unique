package com.ssafy.unique.api.response;

import com.ssafy.unique.db.entity.Market;
import com.ssafy.unique.db.entity.Member;
import com.ssafy.unique.db.entity.Nft;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NftDetailResultRes {
    private int result;
    private Nft nft;
    private Member OwnerMember;
    private Member AuthorMember;
    private List<Market> marketList;
}
