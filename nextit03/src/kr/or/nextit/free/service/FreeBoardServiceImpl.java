package kr.or.nextit.free.service;

import java.util.List;

import kr.or.nextit.common.vo.PagingVO;
import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.exception.BizNotFoundException;
import kr.or.nextit.exception.BizPasswordNotMatchedException;
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
	public List<FreeBoardVO> getBoardList(PagingVO pagingVO) throws BizNotEffectedException {
		// TODO Auto-generated method stub
		
		int totalRowCount = freeBoardDao.getTotalRowCount();
		pagingVO.setTotalRowCount(totalRowCount);
		pagingVO.pageSetting();
		System.out.println("pagingVO.toString() : "+ pagingVO.toString());
		
		
		List<FreeBoardVO> freeBoardList =  freeBoardDao.getBoardList(pagingVO);
		
		if(freeBoardList == null) {
			throw new BizNotEffectedException();
		}
		
		return freeBoardList;
	}

	
	@Override
	public FreeBoardVO getBoard(String boNo) throws BizNotEffectedException {
		// TODO Auto-generated method stub
		System.out.println("getBoard_boNo: "+ boNo);
		
		if(boNo != null && !boNo.equals("")) {
			FreeBoardVO freeBoard = freeBoardDao.getBoard(boNo);
			
			if(freeBoard == null ) {
				throw new BizNotEffectedException();
			}
			return freeBoard;
		}else {
			throw new BizNotEffectedException();
		}

		
		
	}

	@Override
	public void increaseHit(String boNo) throws BizNotEffectedException {
		// TODO Auto-generated method stub
		
		if(boNo != null && !boNo.equals("")) {
			int cnt = freeBoardDao.increaseHit(boNo);
			
			if( cnt != 1) {
				throw new BizNotEffectedException();
			}
		}else {
			throw new BizNotEffectedException();
		}
	}

	@Override
	public void modifyBoard(FreeBoardVO freeBoard) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		// TODO Auto-generated method stub

		if(freeBoard.getBoNo() != null && ! freeBoard.getBoNo().equals("")) {
			FreeBoardVO  vo = freeBoardDao.getBoard(freeBoard.getBoNo());
			if( vo==null) {
				throw new BizNotFoundException();
			}
			if(!vo.getBoPass().equals(freeBoard.getBoPass())) {
				throw new BizPasswordNotMatchedException();
			}
			
			int resultCnt = freeBoardDao.updateBoard(freeBoard);
			if(resultCnt != 1 ){ 
				throw new BizNotEffectedException(); 
			}
		}else {
			throw new BizNotEffectedException();
		}
	}

	@Override
	public void deleteBoard(FreeBoardVO freeBoard) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		// TODO Auto-generated method stub
		
		if(freeBoard.getBoNo() != null && ! freeBoard.getBoNo().equals("")) {
			FreeBoardVO  vo = freeBoardDao.getBoard(freeBoard.getBoNo());
			if( vo==null) {
				throw new BizNotFoundException();
			}
			if(!vo.getBoPass().equals(freeBoard.getBoPass())) { 
				throw new BizPasswordNotMatchedException(); 
			}
			int resultCnt = freeBoardDao.deleteBoard(freeBoard); 
			if(resultCnt != 1 ){ 
				throw new BizNotEffectedException(); 
			}
		}else {
			throw new BizNotEffectedException();
		}
		
	}

}
