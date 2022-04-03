package com.ssafy.unique.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NftParamReq {

    private int offset;
    private int limit;

    private String searchWord;
}
