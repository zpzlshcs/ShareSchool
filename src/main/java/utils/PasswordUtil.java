package utils;

import org.springframework.util.StringUtils;

public class PasswordUtil {
	public static String checkPassword(String pwd){  
        if(StringUtils.isEmpty(pwd)){  
            return "不能为空";  
        }  
        if(pwd.length() < 6){  
            return "长度必须大于6位";  
        }  
        if(pwd.length() > 15){
        	return "长度不得大于15位";
        }
        if(!pwd.matches("^[a-zA-z](.*)") ){  
            return "必须以字母开头";
        }  
        if(!pwd.matches("(.*)\\d+(.*)")){
        	return "必须包含数字";
        }
        return "";  
    } 
	public static void main(String[] args) {
		System.out.println(checkPassword("qqq12312312qweqwe3"));
	}
}
