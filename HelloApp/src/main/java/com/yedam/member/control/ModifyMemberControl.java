package com.yedam.member.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.member.domain.MemberVO;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceImpl;

public class ModifyMemberControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("email");
		String pw = req.getParameter("password");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		MemberVO vo = new MemberVO();

		vo.setEmail(id);
		vo.setPassword(pw);
		vo.setPhone(phone);
		vo.setPassword(address);;

		MemberService service = new MemberServiceImpl();
		boolean result = service.modifyMember(vo);
		
		if(result) {
			return "noticeList.do";
		}else {
			return "modifyMember.do";
		}
	}
	}

