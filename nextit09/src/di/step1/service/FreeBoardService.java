package di.step1.service;

import di.step2.dao.DataBaseDriver;
import di.step2.dao.MysqlDriver;
import di.step2.dao.OracleDriver;

public class FreeBoardService {
	// 디비 연결
	/// DataBaseDriver driver = new OracleDriver();
	// DataBaseDriver driver = new MysqlDriver();
	
	private DataBaseDriver driver; 
	
	public FreeBoardService() {}
	
	public FreeBoardService(DataBaseDriver driver) {
		this.driver = driver;
	}

	public void insertBoard() {
		driver.getConnection();
	}
}
