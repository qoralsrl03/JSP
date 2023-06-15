package di.step1.service;

import di.step1.dao.DataBaseDriver;
import di.step1.dao.MysqlDriver;
import di.step1.dao.OracleDriver;

public class FreeBoardService {
	// 디비 연결
	/// DataBaseDriver driver = new OracleDriver();
	DataBaseDriver driver = new MysqlDriver();

	

	public void insertBoard() {
		driver.getConnection();
	}
}
