package kr.or.nextit.free.service;

import java.util.List;

import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.free.vo.FreeBoardVO;

public interface IFreeBoardService {

	/**
	 * To register free_board
	 * @author ssam
	 * @param freeBoard
	 * @throws BizNotEffectedException
	 */
	public void registerBoard(FreeBoardVO freeBoard) throws BizNotEffectedException;
	
	
	public List<FreeBoardVO> getBoardList() throws BizNotEffectedException;
	
}
