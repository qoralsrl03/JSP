package kr.or.nextit.free.dao;

import java.util.List;

import kr.or.nextit.common.vo.PagingVO;
import kr.or.nextit.free.vo.FreeBoardVO;

public interface IFreeBoardDao {

	/**
	 * To register free_board
	 * @author ssam
	 * @param freeBoard
	 * @return int
	 */
	int insertBoard(FreeBoardVO freeBoard);

	List<FreeBoardVO> getBoardList(PagingVO pagingVO);

	int getTotalRowCount();
	
	public FreeBoardVO getBoard(String boNo);

	public int increaseHit(String boNo);

	public int updateBoard(FreeBoardVO freeBoard);

	public int deleteBoard(FreeBoardVO freeBoard);

}
