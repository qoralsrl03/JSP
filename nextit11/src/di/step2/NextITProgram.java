package di.step2;

import di.step2.service.FreeBoardService;
import di.step2.service.MemberService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import di.step2.dao.DataBaseDriver;
import di.step2.dao.MysqlDriver;
import di.step2.dao.OracleDriver;

public class NextITProgram {
	public static void main(String[] args) {
		//DataBaseDriver driver = new OracleDriver();
		//DataBaseDriver driver = new MysqlDriver();
		
		ApplicationContext context =  new GenericXmlApplicationContext("spring/step2.xml");
			
		
		
		//글쓰기 처리
		//FreeBoardService free = new FreeBoardService(driver);
		//free.insertBoard();
		
		FreeBoardService free =context.getBean(FreeBoardService.class);
		free.insertBoard();
		
		MemberService member = context.getBean(MemberService.class);
		member.insertMember();
	}
}
