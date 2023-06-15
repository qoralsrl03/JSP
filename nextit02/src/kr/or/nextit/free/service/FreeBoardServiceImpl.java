package kr.or.nextit.free.service;

import java.util.List;

import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.free.dao.FreeBoardDaoImpl;
import kr.or.nextit.free.dao.IFreeBoardDao;
import kr.or.nextit.free.vo.FreeBoardVO;

public class FreeBoardServiceImpl implements IFreeBoardService{

	IFreeBoardDao freeBoardDao = new FreeBoardDaoImpl();	
		
	@Override
	public void registerBoard(FreeBoardVO freeBoard) throws BizNotEffectedException {
		// TODO Auto-generated method stub
		
		if(freeBoard.getBoTitle() ==null || freeBoard.getBoTitle().equals("")) {
			throw new BizNotEffectedException();
		}
		
		int resultCnt = freeBoardDao.insertBoard(freeBoard);
		
		if(resultCnt != 1) {
			throw new BizNotEffectedException();
		}
		
	}

	@Override
	public List<FreeBoardVO> getBoardList() throws BizNotEffectedException {
		// TODO Auto-generated method stub
		
		List<FreeBoardVO> freeBoardList =  freeBoardDao.getBoardList();
		
		if(freeBoardList == null) {
			throw new BizNotEffectedException();
		}
		
		return freeBoardList;
	}

}
