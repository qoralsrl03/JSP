package kr.or.nextit.code.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import kr.or.nextit.code.mapper.ICommCodeMapper;
import kr.or.nextit.code.vo.CodeVO;
import kr.or.nextit.common.util.NextITSqlSessionFactory;


//@Component
//@Controller
//@Repository
@Service("codeService")
public class CommCodeServiceImpl implements ICommCodeService{
 
	@Autowired
	private ICommCodeMapper codeMapper;
	
	
	@Override
	public List<CodeVO> getCodeListByParent(String commParent) {
	
		List<CodeVO> codeList= codeMapper.getCodeListByParent(commParent);
		return codeList;
		
	}
	
	
}
