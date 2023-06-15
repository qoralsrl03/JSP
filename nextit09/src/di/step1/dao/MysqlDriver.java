package di.step1.dao;

public class MysqlDriver implements DataBaseDriver{

	public void getConnection() {
		System.out.println("MySQl이 연결되었습니다.");
	}

}
