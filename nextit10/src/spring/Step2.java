package spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import di.step2.dao.MysqlDriver;
import di.step2.dao.OracleDriver;
import di.step2.service.FreeBoardService;
import di.step2.service.MemberService;

@Configuration
public class Step2 {
	public Step2() {
		System.out.println("(@Configuration) Step2");
	}
	
	@Bean("mysql")
	public MysqlDriver mysqlDriver() {
		return new MysqlDriver();
	}
	
	@Bean("oracle")
	public OracleDriver oracleDriver() {
		return new OracleDriver();
	}
	
	@Bean("free")
	public FreeBoardService freeBoardService() {
		return new FreeBoardService();
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService();
	}
}
