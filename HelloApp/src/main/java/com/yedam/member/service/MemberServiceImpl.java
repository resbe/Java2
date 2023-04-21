package com.yedam.member.service;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.member.domain.MemberVO;
import com.yedam.member.mapper.MemberMapper;
import com.yedam.notice.domain.NoticeVO;

public class MemberServiceImpl implements MemberService {
	SqlSession session = DataSource.getInstance().openSession(true);
	MemberMapper mapper = session.getMapper(MemberMapper.class);
	
	@Override
	public MemberVO loginCheck(MemberVO vo) {
		return mapper.loginCheck(vo);
	}
	
	public boolean modifyMember(MemberVO vo) {
		return mapper.modifyMember(vo) == 1;
	}
	
}
