package di.step3;

import di.step3.service.FreeBoardService;
import di.step3.dao.DataBaseDriver;
import di.step3.dao.MysqlDriver;
import di.step3.dao.OracleDriver;

public class NextITProgram {
	public static void main(String[] args) {
		DataBaseDriver driver = new OracleDriver();
		//DataBaseDriver driver = new MysqlDriver();
		
		//글쓰기 처리
		FreeBoardService free = new FreeBoardService();
		free.setDriver(driver);
		free.insertBoard();
	}
}
