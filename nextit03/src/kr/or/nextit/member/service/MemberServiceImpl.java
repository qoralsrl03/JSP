package kr.or.nextit.member.service;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.nextit.exception.BizDuplicateKeyException;
import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.exception.BizNotFoundException;
import kr.or.nextit.exception.BizPasswordNotMatchedException;
import kr.or.nextit.member.dao.IMemberDao;
import kr.or.nextit.member.dao.MemberDaoImpl;
import kr.or.nextit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {

	IMemberDao memberDao = new MemberDaoImpl();
	

	@Override
	public void registerMember(MemberVO member) throws BizDuplicateKeyException, BizNotEffectedException {
		// TODO Auto-generated method stub
		System.out.println("void registerMember");
		
		if(member.getMemId() != null && !member.getMemId().equals("")) {
			MemberVO  vo = memberDao.getMember(member.getMemId());
			if( vo != null ) {
				throw new BizDuplicateKeyException();
			}
			int resultCnt = memberDao.insertMember(member);
			if (resultCnt != 1) {
				throw new BizNotEffectedException();
			} 
			int resultCnt2 = memberDao.insertUserRole(member);
			if (resultCnt2 != 1) {
				throw new BizNotEffectedException();
			}
		}
	}

	
	@Override
	public void loginCheck(MemberVO member, HttpServletRequest request, HttpServletResponse response) throws BizNotEffectedException {
		// TODO Auto-generated method stub
		System.out.println("이거 작동함");
		MemberVO vo = null;
		if(member.getMemId()==null || member.getMemId().equals("")) {
			try {
				response.sendRedirect(request.getContextPath()+"/login/login.jsp?msg=null");
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			vo = memberDao.loginCheck(member);
		}
		
		try {
			if(vo == null){
				System.out.println("member Á€ºžžŠ ºÒ·¯¿ÀÁö žøÇÏ¿ŽœÀŽÏŽÙ. ");
				response.sendRedirect(request.getContextPath()+"/login/login.jsp?msg=fail");
			}else{
				System.out.println("success login");
				HttpSession session = request.getSession();
				session.setAttribute("memberVO", vo);
					
				String rememberMe = member.getRememberMe();
				if (rememberMe != null && rememberMe.equals("Y")) {
					System.out.println("rememberMe is Y");
					Cookie cookie= new Cookie("rememberMe", member.getMemId());
					cookie.setMaxAge(60*60*24); 
					cookie.setHttpOnly(true);
					//cookie.setSecure(true);
					response.addCookie(cookie);
				}else{
					Cookie cookie= new Cookie("rememberMe", "");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				response.sendRedirect(request.getContextPath()+"/home/home.jsp");
			}
		} catch (Exception e) {
			throw new BizNotEffectedException();
		}
		
	}


	@Override
	public MemberVO getMember(String memId) throws BizNotEffectedException {
		// TODO Auto-generated method stub
		
		MemberVO member = null;
		if(memId != null && ! memId.equals("")) {
			member = memberDao.getMember(memId);
		}
		if(member == null) {
			throw new BizNotEffectedException();
		}
		return member;
	}

	@Override	
	public void modifyMember(MemberVO member) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		// TODO Auto-generated method stub
		
		MemberVO vo = null;
		if(member.getMemId() != null && ! member.getMemId().equals("")) {
			vo = memberDao.getMember(member.getMemId());
		}
		
		if(vo == null) {
			throw new BizNotFoundException();
		}
		if( !vo.getMemPass().equals(member.getMemPass())) {
			throw new BizPasswordNotMatchedException();
		}
		
		int resultCnt = memberDao.updateMember(member);
		
		if(resultCnt != 1){
			throw new BizNotEffectedException();
		}
	
	}

	@Override
	public void removeMember(MemberVO member) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		// TODO Auto-generated method stub
		
		MemberVO vo = null;
		if(member.getMemId() != null && ! member.getMemId().equals("")) {
			vo = memberDao.getMember(member.getMemId());
		}
		
		if( vo == null) {
			throw new BizNotFoundException();
		}
		if( !vo.getMemPass().equals(member.getMemPass()) ){
			throw new BizPasswordNotMatchedException();
		}
		
		int resultCnt = memberDao.deleteMember(member);
		if(resultCnt != 1){
			throw new BizNotEffectedException();
		}
	}



 

}