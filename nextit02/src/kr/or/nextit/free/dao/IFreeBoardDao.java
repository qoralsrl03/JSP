package kr.or.nextit.free.dao;

import java.util.List;

import kr.or.nextit.free.vo.FreeBoardVO;

public interface IFreeBoardDao {

	/**
	 * To register free_board
	 * @author ssam
	 * @param freeBoard
	 * @return int
	 */
	int insertBoard(FreeBoardVO freeBoard);

	List<FreeBoardVO> getBoardList();

}
