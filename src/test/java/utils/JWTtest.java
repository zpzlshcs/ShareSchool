package utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import mapper.OrderMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtBuilder;  

public class JWTtest {


	public static void main(String[] args) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();  
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", "999");
		String str = createJWT(map);
		Claims  claims = parseJWT(str);  
        String userName = (String) claims.get("userName");  
        System.out.println(claims);  
	}

	public static String createJWT(Map<String,Object> map) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId("666")                                      // JWT_ID
                .setAudience("777")                                // 接受者
                .setClaims(map)                                // 自定义属性
                .setSubject("获取数据密匙")                                 // 主题
                .setIssuer("服务器")                                  // 签发者
                .setIssuedAt(new Date())                        // 签发时间
                .setNotBefore(new Date())                       // 失效时间
                .setExpiration(DateUtil.get30AfterDate() )      // 过期时间
                .signWith(signatureAlgorithm, secretKey);           // 签名算法以及密匙
        return builder.compact();
    }
	/** 
     * 由字符串生成加密key 
     * @return 
     */  
    private static SecretKey generalKey(){  
        String stringKey = "GzpzG_shareschool";  
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
    public static Claims parseJWT(String jwt) throws Exception{  
        SecretKey key = generalKey();  
        System.out.println(Jwts.parser().setSigningKey(key).parseClaimsJws(jwt));
        Claims claims = Jwts.parser()           
           .setSigningKey(key)  
           .parseClaimsJws(jwt).getBody();  
  
        return claims;  
    }  
}
