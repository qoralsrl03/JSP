package di.step2.dao;

public class OracleDriver implements DataBaseDriver{

	public void getConnection() {
		System.out.println("Oracle이 연결되었습니다.");
	}

}
