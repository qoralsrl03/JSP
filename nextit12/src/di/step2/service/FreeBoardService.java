package di.step2.service;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import di.step2.dao.DataBaseDriver;
import di.step2.dao.MysqlDriver;
import di.step2.dao.OracleDriver;
import di.step2.dao.TiberoDriver;

public class FreeBoardService {
	/*
	 * @Autowired
	 * 
	 * @Qualifier("oracle") private DataBaseDriver driver;
	 */

	/*
	 * @Inject
	 * 
	 * @Qualifier("mysql") private DataBaseDriver driver;
	 */

	/*
	 * @Resource(name="oracle") private DataBaseDriver driver;
	 */

	@Autowired(required = false)
	@Qualifier("tibero1")
	private TiberoDriver driver;

	public void insertBoard() {
		if (driver != null) {
			driver.getConnection();
		}
	}
}
