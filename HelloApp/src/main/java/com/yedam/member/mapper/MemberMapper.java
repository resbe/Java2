package com.yedam.member.mapper;

import com.yedam.member.domain.MemberVO;
import com.yedam.notice.domain.NoticeVO;

public interface MemberMapper {
	public MemberVO loginCheck(MemberVO vo);
	public int modifyMember(MemberVO vo);

}
