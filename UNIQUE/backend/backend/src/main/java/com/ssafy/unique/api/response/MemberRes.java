package com.ssafy.unique.api.response;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberRes {
	
	private Long memberSeq;
	private String memberId;
	private String memberAddress;
	private String profileImageUrl;
	private boolean authority;
	private String memberBio;
	private Long ssf;
	private Date regDt;
//	private String memberEmail;
//	private String memberName;
//	private String memberNickname;
//	private String memberPhone;
//	private String memberBod;
//	private String memberProfileImageUrl;

}
