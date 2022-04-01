package com.ssafy.unique.api.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExhibitionReq {
	private String title;
	private String description;
	private String type;
	private List<Long> nftSeqList;
}
