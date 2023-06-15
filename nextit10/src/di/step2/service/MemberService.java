package di.step2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import di.step2.dao.DataBaseDriver;

public class MemberService {

	@Autowired
	@Qualifier("oracle")
	private DataBaseDriver driver;
	
	public void insertMember() {
		driver.getConnection();
	}
	
}
