package kr.or.nextit.free.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.nextit.common.util.NextITSqlSessionFactory;
import kr.or.nextit.common.vo.PagingVO;
import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.exception.BizNotFoundException;
import kr.or.nextit.exception.BizPasswordNotMatchedException;
import kr.or.nextit.free.dao.IFreeBoardDao;
import kr.or.nextit.free.vo.FreeBoardSearchVO;
import kr.or.nextit.free.vo.FreeBoardVO;

public class FreeBoardServiceImpl implements IFreeBoardService {
	SqlSessionFactory sqlSessionFactory = NextITSqlSessionFactory.getSqlSessionFactory();

	//IFreeBoardDao freeBoardDao = new FreeBoardDaoImpl();

	@Override
	public void registerBoard(FreeBoardVO freeBoard) throws BizNotEffectedException {
		System.out.println("void registerBoard");

		SqlSession sqlSession = sqlSessionFactory.openSession(true); // true 는 auto commit;
		IFreeBoardDao freeDao = sqlSession.getMapper(IFreeBoardDao.class);

		if (freeBoard.getBoTitle() == null || freeBoard.getBoTitle().equals("")) {
			throw new BizNotEffectedException();
		}

		String boNo = freeDao.getFreeBoardKey();
		freeBoard.setBoNo(boNo);
		
		int resultCnt = freeDao.insertBoard(freeBoard);

		if (resultCnt != 1) {
			throw new BizNotEffectedException();
		}

	}

	@Override
	public List<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO) throws BizNotEffectedException {
		// TODO Auto-generated method stub

		SqlSession sqlSession = sqlSessionFactory.openSession(true); // true 는 auto commit;
		IFreeBoardDao freeDao = sqlSession.getMapper(IFreeBoardDao.class);
		
		int totalRowCount = freeDao.getTotalRowCount(searchVO);
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		System.out.println("searchVO.toString() : " + searchVO.toString());

		List<FreeBoardVO> freeBoardList = freeDao.getBoardList(searchVO);
		if (freeBoardList == null) {
			throw new BizNotEffectedException();
		}

		return freeBoardList;
	}

	@Override
	public FreeBoardVO getBoard(String boNo) throws BizNotEffectedException {
		// TODO Auto-generated method stub
		System.out.println("getBoard_boNo: " + boNo);
		
		SqlSession sqlSession = sqlSessionFactory.openSession(true); // true 는 auto commit;
		IFreeBoardDao freeDao = sqlSession.getMapper(IFreeBoardDao.class);

		if (boNo != null && !boNo.equals("")) {
			FreeBoardVO freeBoard = freeDao.getBoard(boNo);

			if (freeBoard == null) {
				throw new BizNotEffectedException();
			}
			return freeBoard;
		} else {
			throw new BizNotEffectedException();
		}

	}

	@Override
	public void increaseHit(String boNo) throws BizNotEffectedException {
		SqlSession sqlSession = sqlSessionFactory.openSession(true); // true 는 auto commit;
		IFreeBoardDao freeDao = sqlSession.getMapper(IFreeBoardDao.class);
		
		if (boNo != null && !boNo.equals("")) {
			int cnt = freeDao.increaseHit(boNo);

			if (cnt != 1) {
				throw new BizNotEffectedException();
			}
		} else {
			throw new BizNotEffectedException();
		}
	}

	@Override
	public void modifyBoard(FreeBoardVO freeBoard)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		SqlSession sqlSession = sqlSessionFactory.openSession(true); // true 는 auto commit;
		IFreeBoardDao freeDao = sqlSession.getMapper(IFreeBoardDao.class);
		
		if (freeBoard.getBoNo() != null && !freeBoard.getBoNo().equals("")) {
			FreeBoardVO vo = freeDao.getBoard(freeBoard.getBoNo());
			if (vo == null) {
				throw new BizNotFoundException();
			}
			if (!vo.getBoPass().equals(freeBoard.getBoPass())) {
				throw new BizPasswordNotMatchedException();
			}

			int resultCnt = freeDao.updateBoard(freeBoard);
			if (resultCnt != 1) {
				throw new BizNotEffectedException();
			}
		} else {
			throw new BizNotEffectedException();
		}
	}

	@Override
	public void deleteBoard(FreeBoardVO freeBoard)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		SqlSession sqlSession = sqlSessionFactory.openSession(true); // true 는 auto commit;
		IFreeBoardDao freeDao = sqlSession.getMapper(IFreeBoardDao.class);

		if (freeBoard.getBoNo() != null && !freeBoard.getBoNo().equals("")) {
			FreeBoardVO vo = freeDao.getBoard(freeBoard.getBoNo());
			if (vo == null) {
				throw new BizNotFoundException();
			}
			if (!vo.getBoPass().equals(freeBoard.getBoPass())) {
				throw new BizPasswordNotMatchedException();
			}
			int resultCnt = freeDao.deleteBoard(freeBoard);
			if (resultCnt != 1) {
				throw new BizNotEffectedException();
			}
		} else {
			throw new BizNotEffectedException();
		}
	}

	@Override
	public void hideBoard(String memId, String boNo) throws BizNotEffectedException {
		SqlSession sqlSession = sqlSessionFactory.openSession(true); // true 는 auto commit;
		IFreeBoardDao freeDao = sqlSession.getMapper(IFreeBoardDao.class);
		
		FreeBoardVO freeBoard = new FreeBoardVO();
		freeBoard.setBoWriter(memId);
		freeBoard.setBoNo(boNo);

		int checkAdmin = freeDao.checkAdmin(freeBoard);
		if (checkAdmin != 1) {
			throw new BizNotEffectedException();
		}

		int resultCnt = freeDao.deleteBoard(freeBoard);
		if (resultCnt != 1) {
			throw new BizNotEffectedException();
		}

	}
}
