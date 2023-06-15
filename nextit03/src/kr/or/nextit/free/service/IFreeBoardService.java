package kr.or.nextit.free.service;

import java.util.List;

import kr.or.nextit.common.vo.PagingVO;
import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.exception.BizNotFoundException;
import kr.or.nextit.exception.BizPasswordNotMatchedException;
import kr.or.nextit.free.vo.FreeBoardVO;

public interface IFreeBoardService {

	/**
	 * To register free_board
	 * @author ssam
	 * @param freeBoard
	 * @throws BizNotEffectedException
	 */
	public void registerBoard(FreeBoardVO freeBoard) throws BizNotEffectedException;
	
	
	//public List<FreeBoardVO> getBoardList() throws BizNotEffectedException;
	public List<FreeBoardVO> getBoardList(PagingVO pagingVO) throws BizNotEffectedException;
	
	
	public FreeBoardVO getBoard(String boNo) throws BizNotEffectedException;
	
	public void increaseHit(String boNo) throws BizNotEffectedException;
	
	public void modifyBoard(FreeBoardVO freeBoard) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException;

	public void deleteBoard(FreeBoardVO freeBoard) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException;

}
