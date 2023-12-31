package kr.or.nextit.member.dao;

import java.util.List;
import java.util.Map;

import kr.or.nextit.common.vo.RoleInfoVO;
import kr.or.nextit.common.vo.UserRoleVO;
import kr.or.nextit.member.vo.MemberSearchVO;
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

	/**
	 * To get user Role
	 * @author ssam
	 * @param member
	 * @return
	 */
	public List<UserRoleVO> getUserRole(MemberVO member);

	/**
	 * To get Member List
	 * @author ssam
	 * @param searchVO
	 * @return
	 */
	public List<MemberVO> getMemberList(MemberSearchVO searchVO);

	/**
	 * To get member count
	 * @author ssam
	 * @param searchVO
	 * @return
	 */
	public int getTotalRowCount(MemberSearchVO searchVO);


	public List<RoleInfoVO> getRoleInfo();
	
	public Map<String, String> getRolename(String role[]);


	public int updateList(List<UserRoleVO> update_list);
	
	
	


	
}