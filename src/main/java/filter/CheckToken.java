package filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;

import exception.CustomException;
import utils.DateUtil;
import utils.TokenBean;

public class CheckToken {
	private static Logger logger = Logger.getLogger(HeaderTokenFilter.class);
	public static boolean check(HttpServletRequest request) throws Exception{
		String token = getToken(request);
		if(JWTtutil.parseJWT(token).getUserLevel()==1)
			return true;
		if(JWTtutil.parseJWT(token).getUserLevel()==2)
			return true;
		if(JWTtutil.parseJWT(token).getUserLevel()==3)
			return true;
		throw new CustomException(103, "请先登录");
	}
    public static boolean check(HttpServletRequest request,Integer userId) throws Exception{
		String token = getToken(request);
    	TokenBean tokenBean = JWTtutil.parseJWT(token);
    	if(tokenBean.getUserLevel()==1||tokenBean.getUserLevel()==2)
			if(tokenBean.getUserId().equals(userId))
				return true;
		if(tokenBean.getUserLevel()==3)
			return true;
		throw new CustomException(103, "无权操作");
	}
    public static boolean checkManager(HttpServletRequest request) throws Exception{
		String token = getToken(request);
    	TokenBean tokenBean = JWTtutil.parseJWT(token);
    	if(tokenBean.getUserLevel()==3)
			return true;
    	throw new CustomException(103, "无权进行操作");
    }
    public static String getToken(HttpServletRequest request) throws IOException{
    	//info(request);
    	return request.getHeader("token");
    }
    public static void info(HttpServletRequest request) throws IOException{
    	String reqURL = request.getRequestURL().toString();
		String ip = request.getRemoteHost();

		InputStream is = request.getInputStream();
		StringBuilder responseStrBuilder = new StringBuilder();
		BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String inputStr;
		while ((inputStr = streamReader.readLine()) != null)
			responseStrBuilder.append(inputStr);
		String parmeter = responseStrBuilder.toString();

		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		StringBuilder sb = new StringBuilder(1000);
		sb.append("-----------------------").append(DateUtil.getDate())
				.append("-------------------------------------\n");
		// 请求方式 post\put\get 等等
		sb.append("RqMethod  : ").append(request.getMethod()).append("\n");
		// 所有的请求参数
		sb.append("Params    : ").append(parmeter).append("\n");
		// 部分请求链接
		sb.append("URI       : ").append(request.getRequestURI()).append("\n");
		// 完整的请求链接
		sb.append("AllURI    : ").append(reqURL).append("\n");
		// 请求方的 ip地址
		sb.append("request IP: ").append(ip).append("\n");
		logger.info(sb.toString());
    }
}
