package filter;

import javax.servlet.http.HttpServletRequest;

import exception.CustomException;
import utils.TokenBean;

public class CheckToken {
	public static boolean check(HttpServletRequest request) throws Exception{
		String token = getToken(request);
		if(JWTtutil.parseJWT(token).getUserLevel()==1)
			return true;
		if(JWTtutil.parseJWT(token).getUserLevel()==2)
			return true;
		if(JWTtutil.parseJWT(token).getUserLevel()==3)
			return true;
		throw new CustomException(103, "注册用户可进行此操作");
	}
    public static boolean check(HttpServletRequest request,Integer userId) throws Exception{
		String token = getToken(request);
    	TokenBean tokenBean = JWTtutil.parseJWT(token);
    	if(tokenBean.getUserLevel()==1||tokenBean.getUserLevel()==2)
			if(tokenBean.getUserId().equals(userId))
				return true;
		if(tokenBean.getUserLevel()==3)
			return true;
		throw new CustomException(103, "相关用户和管理员可进行此操作");
	}
    public static boolean checkManager(HttpServletRequest request) throws Exception{
		String token = getToken(request);
    	TokenBean tokenBean = JWTtutil.parseJWT(token);
    	if(tokenBean.getUserLevel()==3)
			return true;
    	throw new CustomException(103, "只有管理员可以进行此操作");
    }
    public static String getToken(HttpServletRequest request){
    	return request.getHeader("token");
    }
}
