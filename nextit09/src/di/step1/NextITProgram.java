package di.step1;

import di.step1.service.FreeBoardService;
import di.step2.dao.DataBaseDriver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NextITProgram {
	public static void main(String[] args) {
		// DataBaseDriver driver = new OracleDriver();
		// DataBaseDriver driver = new MysqlDriver();

		ApplicationContext context = new ClassPathXmlApplicationContext("spring/step1.xml");
		
		//스프링을 사용해서 new 를 없앰
		DataBaseDriver driver = (DataBaseDriver) context.getBean("driver");
		
		
		// 글쓰기 처리
		FreeBoardService free = new FreeBoardService(driver);
		free.insertBoard();
	}
}
