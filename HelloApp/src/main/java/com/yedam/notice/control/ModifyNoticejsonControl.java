package com.yedam.notice.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class ModifyNoticejsonControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//id,title, subject 받아와서 값을 변경.
		NoticeService service = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		String title = req.getParameter("title");
		String subject = req.getParameter("subject"); 
		String id = req.getParameter("id"); 
		
		vo.setNoticeTitle(title);
		vo.setNoticeSubject(subject);
		vo.setNoticeId(Integer.parseInt(id));
		// id를 기준으로 한건 변경된 값을 조회.
		
		boolean result = service.modifyNotice(vo);
		
		String json = "";
		
		Map<String, Object> map = new HashMap<>();
		
		if(result) {
			vo = service.getNotice(Integer.parseInt((id)));
			map.put("retCode", "Success");
			map.put("retVal", vo);
		}else {
			map.put("retCode","Fail");
			map.put("retVal", null);
		}
		Gson gson = new GsonBuilder().create();//gson 객체
		json = gson.toJson(map);
	
		return json + ".json";
		
	}

}
