package com.ssafy.unique.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExhibitionParamReq {
	private int limit;
	private int offset;
	private String type;
}
