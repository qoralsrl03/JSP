package di.step2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import di.step2.dao.DataBaseDriver;
import di.step2.dao.MysqlDriver;
import di.step2.dao.OracleDriver;

public class FreeBoardService {
	@Autowired
	@Qualifier("oracle")
	private DataBaseDriver driver; 
	
	public FreeBoardService() {}
	

	public void insertBoard() {
		driver.getConnection();
	}
}
