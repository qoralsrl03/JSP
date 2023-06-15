package kr.or.nextit.common.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class NextITEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		System.out.println("----  start EncodingFilter");
		
		//사용자가 요청할때 한글깨짐 방지
		request.setCharacterEncoding("utf-8");
		
		chain.doFilter(request, response);
		
		//서버가 사용자에게 응답할때 처리
		response.setContentType("text/html; charset=utf-8");
		
		System.out.println("----  end EncodingFilter");
		
	}

}
