package kr.or.nextit.member.service;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.nextit.common.util.NextITSqlSessionFactory;
import kr.or.nextit.common.vo.RoleInfoVO;
import kr.or.nextit.common.vo.UserRoleVO;
import kr.or.nextit.exception.BizDuplicateKeyException;
import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.exception.BizNotFoundException;
import kr.or.nextit.exception.BizPasswordNotMatchedException;
import kr.or.nextit.member.mapper.IMemberMapper;
import kr.or.nextit.member.vo.MemberSearchVO;
import kr.or.nextit.member.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements IMemberService {

	
	@Inject
	private IMemberMapper memMapper;

	@Override
	public void registerMember(MemberVO member) throws BizDuplicateKeyException, BizNotEffectedException {
		System.out.println("void registerMember");
		
		
		 if(member.getMemId() != null && !member.getMemId().equals("")) {
				
				MemberVO  vo = memMapper.getMember(member.getMemId());
				if( vo != null ) {
					throw new BizDuplicateKeyException();
				}
				
				int resultCnt = memMapper.insertMember(member);
				if (resultCnt != 1) {
					throw new BizNotEffectedException();
				} 
				int resultCnt2 = memMapper.insertUserRole(member);
				if (resultCnt2 != 1) {
					throw new BizNotEffectedException();
				}
			}
	}

	
	@Override
	public boolean loginCheck(MemberVO member, HttpServletRequest request, HttpServletResponse response) throws BizNotEffectedException {

 
		MemberVO vo = null;
		if(member.getMemId()==null || member.getMemId().equals("")) {
			return false;
		}else {
			vo = memMapper.loginCheck(member);
		}
		try {
			if(vo == null){
				System.out.println("do not get member info ");
				return false;
			}else{
				System.out.println("success login");
				
				List<UserRoleVO> userRoleList = memMapper.getUserRole(member);
				if(userRoleList != null) {
					vo.setUserRoleList(userRoleList);
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("memberVO", vo);
					
				String rememberMe = member.getRememberMe();
				if (rememberMe != null && rememberMe.equals("Y")) {
					System.out.println("rememberMe is Y");
					Cookie cookie= new Cookie("rememberMe", member.getMemId());
					cookie.setMaxAge(60*60*24); 
					cookie.setHttpOnly(true);
					response.addCookie(cookie);
				}else{
					Cookie cookie= new Cookie("rememberMe", "");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				return true;
			}
		} catch (Exception e) {
			throw new BizNotEffectedException();
		} 
	}


	@Override
	public MemberVO getMember(String memId) throws BizNotEffectedException {
		
		 
		MemberVO member = null;
		if(memId != null && ! memId.equals("")) {
			//member = memberDao.getMember(memId);
			member = memMapper.getMember(memId);
		}
		if(member == null) {
			throw new BizNotEffectedException();
		}
		return member;
		 
	
	}

	@Override	
	public void modifyMember(MemberVO member) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		
		
		 
		MemberVO vo = null;
		if(member.getMemId() != null && ! member.getMemId().equals("")) {
			vo = memMapper.getMember(member.getMemId());
		}
		
		if(vo == null) {
			throw new BizNotFoundException();
		}
		if( !vo.getMemPass().equals(member.getMemPass())) {
			throw new BizPasswordNotMatchedException();
		}
		
		int resultCnt = memMapper.updateMember(member);
		
		if(resultCnt != 1){
			throw new BizNotEffectedException();
		}
		 
		
		
	
	}

	@Override
	public void removeMember(MemberVO member) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		
		
		MemberVO vo = null;
		if(member.getMemId() != null && ! member.getMemId().equals("")) {
			vo = memMapper.getMember(member.getMemId());
		}
		
		if( vo == null) {
			throw new BizNotFoundException();
		}
		if( !vo.getMemPass().equals(member.getMemPass()) ){
			throw new BizPasswordNotMatchedException();
		}
		
		int resultCnt = memMapper.deleteMember(member);
		if(resultCnt != 1){
			throw new BizNotEffectedException();
		}
	 
		
	}

	@Override
	public List<MemberVO> getMemberList(MemberSearchVO searchVO) throws BizNotFoundException {

	
		int totalRowCount  = memMapper.getTotalRowCount(searchVO);
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		System.out.println("searchVO.toString() : "+searchVO.toString());

		List<MemberVO> memberVO = memMapper.getMemberList(searchVO);
		
		if(memberVO == null) {
			throw new BizNotFoundException();
		}
		return memberVO;
	}


	@Override
	public void removeMultiMember(String memMultiId) throws BizNotEffectedException {
		
		System.out.println("memMultiId: "+ memMultiId);
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		try { 
				List<Object> list  = objectMapper.readValue(memMultiId, new TypeReference<List<Object>>(){});
				System.out.println("list: "+ list);
				
				if(list.size() == 0) {
					throw new BizNotEffectedException();
				}
				
				for(int i=0; i<list.size(); i++) {
					String memId = (String) list.get(i);
					MemberVO member = new MemberVO();
					member.setMemId(memId);
					//int resultCnt = memberDao.deleteMember(member);
					int resultCnt = memMapper.deleteMember(member);
					if(resultCnt ==0) {
						throw new BizNotEffectedException();	
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizNotEffectedException();
		}  
	}


	@Override
	public MemberVO getMemberRole(String memId) throws BizNotEffectedException {
		System.out.println("MemberServiceImpl memId: "+ memId);
		
		
		MemberVO member = null;
		if(memId != null && ! memId.equals("")) {
			member =  memMapper.getMember(memId);
		}
		if(member == null) {
			throw new BizNotEffectedException();
		}
		
		List<UserRoleVO>  userRoleList =memMapper.getUserRole(member);
		
		if(userRoleList == null) {
			throw new BizNotEffectedException();
		}
		
		member.setUserRoleList(userRoleList);
		
		return member;
	 
	
	}


	@Override
	public List<RoleInfoVO> getRoleInfo() throws BizNotEffectedException {
		
		
		 
		List<RoleInfoVO> roleInfoList = memMapper.getRoleInfo();
		
		if(roleInfoList ==null) {
			throw new BizNotEffectedException();
		}
		return roleInfoList;
	 
	}

	@Override
	public void updateUserRole(String memId, String[] roles) throws BizNotEffectedException {

		System.out.println("roles.length :"+ roles.length);
		
		
		if( memId != null && ! memId.equals("")) {
			memMapper.deleteUserRole(memId);
			
			if(roles.length >0 ) {
				for(int i=0; i<roles.length; i++) {
					memMapper.insertMultiRole(memId, roles[i]);
				}
			}
		}else {
			throw new BizNotEffectedException();
		}
	 
		
		
	}
 

}