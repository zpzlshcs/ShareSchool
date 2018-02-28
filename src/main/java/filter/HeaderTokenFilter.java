package filter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import exception.CustomException;
import utils.DateUtil;
import utils.RequestUtil;

public class HeaderTokenFilter implements HandlerInterceptor {

	private static Logger logger = Logger.getLogger(HeaderTokenFilter.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		 if(request.getHeader("token")==null)
		 throw new CustomException(103, "无权访问");
		 logger.info("访问："+request.getRequestURI());
		
		return true;
	}
}
