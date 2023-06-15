package di.step2;

import di.step2.service.FreeBoardService;
import di.step2.dao.DataBaseDriver;
import di.step2.dao.MysqlDriver;
import di.step2.dao.OracleDriver;

public class NextITProgram {
	public static void main(String[] args) {
		//DataBaseDriver driver = new OracleDriver();
		DataBaseDriver driver = new MysqlDriver();
		
		//글쓰기 처리
		FreeBoardService free = new FreeBoardService(driver);
		free.insertBoard();
	}
}
