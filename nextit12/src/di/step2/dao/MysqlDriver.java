package di.step2.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("mysql")
public class MysqlDriver implements DataBaseDriver{

	public void getConnection() {
		System.out.println("MySQl이 연결되었습니다.");
	}

}
