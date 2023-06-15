package di.step2.dao;

import org.springframework.stereotype.Repository;

@Repository
public class TiberoDriver implements DataBaseDriver{

	@Override
	public void getConnection() {
		System.out.println("Tiber가 연결되었습니다.");
	}

	public void insertBoard() {
		System.out.println();
	}
}
