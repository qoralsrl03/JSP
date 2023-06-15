package spring;

import org.springframework.context.annotation.Bean;

import di.step2.dao.OracleDriver;
import di.step2.service.FreeBoardService;

public class Step2 {
	public Step2() {
		System.out.println("class Step2");
	}
	
	@Bean
	public FreeBoardService freeBoardService() {
		return new FreeBoardService();
	}
	
	@Bean("oracle")
	public OracleDriver oracleDriver() {
		return new OracleDriver();
	}
}
