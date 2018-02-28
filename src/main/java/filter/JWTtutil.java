package filter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

import exception.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import utils.DateUtil;
import utils.TokenBean;

/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: JWTtutil.java
* @Description: json web token的工具类
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月6日 下午1:38:08 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月6日     GzpzG           v1.0.0               创建
 */
public class JWTtutil {
	

    static final String stringKey = "GzpzG_shareschool"; 
    static final String JWItheme = "share_key";
    static final String Issuername = "GzpzG";
    /**
     * 传入自定数据，获取token
    * @param:
     * @throws CustomException 
    * @return：
    * @throws：
     */
	public static String createJWT(TokenBean tokenBean) throws CustomException {
		if(tokenBean.getUserId()==null)
        	throw new CustomException(101, "token中缺少userId");
        if(tokenBean.getUserLevel()==null)
        	throw new CustomException(101, "token中缺少userLevel");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", tokenBean.getUserId());
		map.put("userLevel", tokenBean.getUserLevel());
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
//                .setId("666")                                      // JWT_ID
//                .setAudience("777")                                // 接受者
                .setClaims(map)                                // 自定义属性
                .setSubject(JWItheme)                                 // 主题
                .setIssuer(Issuername)                                  // 签发者
                .setIssuedAt(new Date())                        // 签发时间
                .setNotBefore(new Date())                       // 失效时间
                .setExpiration(DateUtil.get30AfterDate())      // 过期时间（一个月）
                .signWith(signatureAlgorithm, secretKey);           // 签名算法以及密匙
        return builder.compact();
    }
	/** 
     * 由字符串生成加密key 
     * @return 
     */  
    private static SecretKey generalKey(){   
        byte[] encodedKey = Base64.decodeBase64(stringKey);  
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");  
        return key;  
    }  
    /** 
     * 解析jwt 
     * @param jwt 
     * @return 
     * @throws Exception 
     */  
    public static TokenBean parseJWT(String jwt) throws Exception{  
        SecretKey key = generalKey();  
        Claims claims = null;
        try{
        	claims = Jwts.parser()           
        	           .setSigningKey(key)  
        	           .parseClaimsJws(jwt).getBody();
        }catch(ExpiredJwtException ex){
        	throw new CustomException(103, "证书失效，请重新登录");
        }catch(IllegalArgumentException e){
        	throw new CustomException(103, "请先登录");
        }
        TokenBean tokenBean = new TokenBean();
        if(claims.get("userId")==null)
        	throw new CustomException(101, "token中缺少userId");
        if(claims.get("userLevel")==null)
        	throw new CustomException(101, "token中缺少userLevel");
        tokenBean.setUserId((Integer)claims.get("userId"));
        tokenBean.setUserLevel((Integer)claims.get("userLevel"));
        return tokenBean;  
    } 
    public static void main(String[] args) throws Exception {
    	TokenBean tokenBean = new TokenBean();
    	tokenBean.setUserId(1);
    	tokenBean.setUserLevel(1);
		String str = createJWT(tokenBean);
		str="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaGFyZV9rZXkiLCJ1c2VyTGV2ZWwiOjMsIm5iZiI6MTUxODAwMTY2NCwiaXNzIjoiR3pwekciLCJleHAiOjE1MjA1OTM2NjQsInVzZXJJZCI6MSwiaWF0IjoxNTE4MDAxNjY0fQ.RmD7skaRgjCzB0Q17Uriddc0w7EMtbxfJOd26PX--PA";
		tokenBean = parseJWT(str);   
		System.out.println(str);
        System.out.println(tokenBean.getUserLevel());  
	}
}
