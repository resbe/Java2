package com.yedam.notice.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Control;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class GetModifyNoticeControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 파일 업로드/ db 입력처리/ 목록이동.
		// 멀트파트요청: 요청정보, 저장경로, 최대파일사이즈, 인코딩, 리네임정책인스턴스.

		// GET방식 요청.
		NoticeService service = new NoticeServiceImpl();
		if (req.getMethod().equals("GET")) {
			String nid = req.getParameter("nid");
			NoticeVO vo = service.getNotice(Integer.parseInt(nid));

			req.setAttribute("noticeInfo", vo);

			return "notice/noticeModify.tiles";

			// POST방식의 요청
		} else if (req.getMethod().equals("POST")) {


			String nid = req.getParameter("nid");
			String title = req.getParameter("title");
			String subject = req.getParameter("subject");
			NoticeVO vo = new NoticeVO();

			vo.setNoticeId(Integer.parseInt(nid));
			vo.setNoticeTitle(title);
			vo.setNoticeSubject(subject);

			boolean result = service.modifyNotice(vo);
			
			if(result) {
				
				return "noticeList.do";
			}else {
				return "modifyNotice.do";
			}
		}

		return null;
	}
}
