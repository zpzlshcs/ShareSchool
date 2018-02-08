package filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import exception.CustomException;

public class HeaderTokenFilter implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
		if(request.getHeader("token")==null)
			throw new CustomException(103, "无权访问");
        return true;
    }
}
