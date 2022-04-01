package com.ssafy.unique.api.response;

import java.util.List;

import com.ssafy.unique.db.entity.Exhibition;
import com.ssafy.unique.db.entity.ExhibitionWork;
import com.ssafy.unique.db.entity.Member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExhibitionRes {
	private int result;
	private Exhibition exhibition;
	private List<ExhibitionWork> exhibitionWorkList;
	private List<Member> memberList;
}
