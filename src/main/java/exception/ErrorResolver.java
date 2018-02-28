package exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import filter.HeaderTokenFilter;
import utils.ResultBean;

@ResponseStatus(value=HttpStatus.OK)
public class ErrorResolver implements HandlerExceptionResolver {

	private static Logger logger = Logger.getLogger(HeaderTokenFilter.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ResultBean<Object> resultBean = new ResultBean<Object>();
		int resultCode = 100;
		String resultMessage = "未知错误";
		if (ex instanceof CustomException) {
			resultCode = ((CustomException) ex).getResultCode();
			if (((CustomException) ex).resultMessage != null)
				resultMessage = ((CustomException) ex).getResultMessage();
		} else if (ex instanceof IndexOutOfBoundsException) {
			resultCode = 104;
			resultMessage = "无相应条件的数据";
		} else if (ex instanceof DataIntegrityViolationException) {
			resultCode = 104;
			resultMessage = "无id相关数据可匹配";
		} else if (ex instanceof HttpMessageNotReadableException) {
			resultCode = 104;
			resultMessage = "参数提交错误";
		} else if (ex instanceof HttpRequestMethodNotSupportedException) {
			resultCode = HttpServletResponse.SC_METHOD_NOT_ALLOWED;
			resultMessage = ex.getClass()+"";
		} else if (ex instanceof HttpMediaTypeNotSupportedException) {
			resultCode = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
			resultMessage = ex.getClass()+"";
		} else if (ex instanceof HttpMediaTypeNotAcceptableException) {
			resultCode = HttpServletResponse.SC_NOT_ACCEPTABLE;
			resultMessage = ex.getClass()+"";
		} else if (ex instanceof MissingServletRequestParameterException) {
			resultCode = HttpServletResponse.SC_BAD_REQUEST;
			resultMessage = ex.getClass()+"";
		} else if (ex instanceof ServletRequestBindingException) {
			resultCode = HttpServletResponse.SC_BAD_REQUEST;
			resultMessage = ex.getClass()+"";
		} else if (ex instanceof ConversionNotSupportedException) {
			resultCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			resultMessage = ex.getClass()+"";
		} else if (ex instanceof TypeMismatchException) {
			resultCode = HttpServletResponse.SC_BAD_REQUEST;
			resultMessage = ex.getClass()+"";
		} else if (ex instanceof HttpMessageNotReadableException) {
			resultCode = HttpServletResponse.SC_BAD_REQUEST;
			resultMessage = ex.getClass()+"";
		} else if (ex instanceof HttpMessageNotWritableException) {
			resultCode = HttpServletResponse.SC_BAD_REQUEST;
			resultMessage = ex.getClass()+"";
		} else if (ex instanceof MethodArgumentNotValidException) {
			resultCode = HttpServletResponse.SC_BAD_REQUEST;
			resultMessage = ex.getClass()+"";
		} else if (ex instanceof MissingServletRequestPartException) {
			resultCode = HttpServletResponse.SC_BAD_REQUEST;
			resultMessage = ex.getClass()+"";
		} else if (ex instanceof BindException) {
			resultCode = HttpServletResponse.SC_BAD_REQUEST;
			resultMessage = ex.getClass()+"";
		} else if (ex instanceof NoHandlerFoundException) {
			resultCode = HttpServletResponse.SC_NOT_FOUND;
			resultMessage = ex.getClass()+"";
		} else {
			resultBean.setResultCode(100);
			resultBean.setResultMessage("未知错误  \n" + ex.getClass() + "\n" + ex.getMessage());
		}
		resultBean.setResultCode(resultCode);
		resultBean.setResultMessage(resultMessage);
		logger.error(resultMessage);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setStatus(HttpStatus.OK);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(sdf);
		String json = "";
		try {
			json = objectMapper.writeValueAsString(resultBean);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ex.printStackTrace();
		modelAndView.setViewName("/WEB-INF/result.jsp");
		modelAndView.addObject("result",json);
		return modelAndView;
	}

}
