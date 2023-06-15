package kr.or.nextit.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NextITIPCheckFilter implements Filter{

	private Map<String, Boolean> denyIpMap = new HashMap<String, Boolean>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		//Filter.super.init(filterConfig);
		
		denyIpMap.put("192.168.1.16", false);
		denyIpMap.put("192.168.1.30", false);
		
	}
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		System.out.println("---- ---- start IPCheckFilter ");
		
		HttpServletRequest req = (HttpServletRequest) request;
		if(denyIpMap.get(req.getRemoteAddr()) !=null) {
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.print("<html><body><h1>해당 아이피는 차단되었습니다.</h1></body></html>");
		}else {
			chain.doFilter(request, response);
		}
		
		System.out.println("---- ---- end IPCheckFilter ");
		
	}

}
