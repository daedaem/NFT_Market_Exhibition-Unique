package com.ssafy.unique.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberRes {
	
	private Long memberSeq;
	private String memberId;
	private String memberAddress;
	private boolean authority;
//	private String memberEmail;
//	private String memberName;
//	private String memberNickname;
//	private String memberPhone;
//	private String memberBod;
//	private String memberProfileImageUrl;
//	private Date memberRegisterDate;

}
