package di.step2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import di.step2.dao.DataBaseDriver;

@Service
public class MemberService {

	/*
	 * @Autowired
	 * 
	 * @Qualifier("mysql") private DataBaseDriver driver;
	 */
	private DataBaseDriver driver;
	
	public MemberService() {}
	
	@Autowired
	public MemberService(@Qualifier("oracle")DataBaseDriver driver) {
		this.driver = driver;
	}
	
	public void insertMember() {
		driver.getConnection();
	}
	
}
