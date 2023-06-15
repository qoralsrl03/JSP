package kr.or.nextit.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.nextit.exception.BizDuplicateKeyException;
import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.exception.BizNotFoundException;
import kr.or.nextit.exception.BizPasswordNotMatchedException;
import kr.or.nextit.member.vo.MemberVO;

public interface IMemberService {

	/**
	 * To register member 
	 * @author nextit
	 * @param member
	 * @throws BizDuplicateKeyException
	 * @throws BizNotEffectedException
	 */
	public void registerMember(MemberVO member) throws BizDuplicateKeyException, BizNotEffectedException;
	
	/**
	 * To check login 
	 * @author nextit
	 * @param member
	 * @param request
	 * @param response
	 * @throws BizNotEffectedException
	 */
	public void loginCheck(MemberVO member, HttpServletRequest request, HttpServletResponse response) throws BizNotEffectedException;

	/**
	 * To get member information
	 * @param memId
	 * @return MemberVO
	 * @throws BizNotEffectedException
	 */
	public MemberVO getMember(String memId) throws BizNotEffectedException;
		
		
	/**
	 * To update member information
	 * @author nextit
	 * @param member
	 * @throws BizNotFoundException
	 * @throws BizPasswordNotMatchedException
	 * @throws BizNotEffectedException
	 */
	public void modifyMember(MemberVO member) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException;

	
	/**
	 * To delete member information
	 * @author nextit
	 * @param member
	 * @throws BizNotFoundException
	 * @throws BizPasswordNotMatchedException
	 * @throws BizNotEffectedException
	 */
	public void removeMember(MemberVO member) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException;

	
	
}