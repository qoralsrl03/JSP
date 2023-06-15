package di.step2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import di.step2.dao.DataBaseDriver;
import di.step2.dao.MysqlDriver;
import di.step2.dao.OracleDriver;

public class FreeBoardService {
	// 디비 연결
	/// DataBaseDriver driver = new OracleDriver();
	// DataBaseDriver driver = new MysqlDriver();
	
	@Autowired
	@Qualifier("mysql")
	private DataBaseDriver driver; 
	
	public FreeBoardService() {}
	
	public FreeBoardService(DataBaseDriver driver) {
		this.driver = driver;
	}

	public void insertBoard() {
		driver.getConnection();
	}
}
