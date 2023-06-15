package kr.or.nextit.home.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.nextit.member.vo.MemberVO;
import kr.or.nextit.servlet.NextITProcess;

public class Home implements NextITProcess {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse resp) {
		// TODO Auto-generated method stub
	
		/*MemberVO memberVO =  (MemberVO)request.getSession().getAttribute("memberVO");
		if(memberVO == null){
			return "redirect:/login/login.do?msg=none";
		}*/
		return "/WEB-INF/views/home/home.jsp";
	}

}
