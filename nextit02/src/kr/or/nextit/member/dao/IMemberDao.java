package kr.or.nextit.member.dao;

import kr.or.nextit.member.vo.MemberVO;

public interface IMemberDao {

	/**
	 * To get member information
	 * @author nextit
	 * @param memId
	 * @return MemberVO
	 */
	public MemberVO getMember(String memId);

	
	/**
	 * To insert member information
	 * @author nextit
	 * @param member
	 * @return int
	 */
	public int insertMember(MemberVO member);

	/**
	 * To insert memeber role
	 * @author nextit
	 * @param member
	 * @return int
	 */
	public int insertUserRole(MemberVO member);
	
	
	/**
	 * To check login
	 * @author nextit
	 * @param member
	 * @return MemberVO
	 */
	public MemberVO loginCheck(MemberVO member);

	/**
	 * To update member information
	 * @author nextit
	 * @param member
	 * @return int
	 */
	public int updateMember(MemberVO member);

	
	/**
	 * To delete member information
	 * @author nextit
	 * @param member
	 * @return int
	 */
	public int deleteMember(MemberVO member);

	

	
	


	
}