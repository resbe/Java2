package com.yedam.notice.domain;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeVO {
	private int noticeId;
	private String noticeWriter;
	private String noticeTitle;
	private String noticeSubject;
	private String attachFile;
	private Date noticeDate;
	private int hitCount;
	
}
