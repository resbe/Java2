package com.yedam.domain;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.notice.mapper.NoticeMapper;
import com.yedam.notice.domain.NoticeVO;

public class sampleExe2 {
	public static void main(String[] args) {
		SqlSessionFactory sqlSessionFactory =
				com.yedam.common.DataSource.getInstance();
		
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			  NoticeMapper mapper = session.getMapper(NoticeMapper.class);
			  
			  for(NoticeVO vo : mapper.noticeList()) {
				  System.out.println(vo);
			  }
		}
	}
}
