package com.bkhech.cloud.commons.auth.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 * 参考官网：https://jwt.io/
 * JWT的数据结构为：A.B.C三部分数据，由字符点"."分割成三部分数据
 * A-header头信息
 * B-payload 有效负荷 一般包括：已注册信息（registered claims），公开数据(public claims)，私有数据(private claims)
 * C-signature 签名信息 是将header和payload进行加密生成的
 * @author guowm
 * @date 2021/3/22
 */
public class JwtHelper {

    private static Logger LOGGER = LoggerFactory.getLogger(JwtHelper.class);

    /**
     * 由字符串生成加密key
     * @return
     */
    public static SecretKey generalKey(){
        // 将 BASE64_SECRET 常量字符串使用 base64 解码成字节数组
        byte[] base64EncodedKeyBytes = Base64.getDecoder().decode(SecretConstant.BASE64_SECRET);
        // 使用 AES 签名算法生成一个签名秘钥Key
        SecretKeySpec key = new SecretKeySpec(base64EncodedKeyBytes, 0, base64EncodedKeyBytes.length, "AES");
        return key;
    }

    /**
     * 生成JWT字符串
     * 格式：A.B.C
     * A-header头信息
     * B-payload 有效负荷
     * C-signature 签名信息 是将header和payload进行加密生成的
     *
     * @param userId     - 用户编号
     * @param userName   - 用户名
     * @param identities - 客户端其他信息（变长参数）
     */
    public static String generateJWT(String userId, String userName, String... identities) {
        //签名算法，选择SHA-256
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //获取当前系统时间
        long nowTimeMillis = System.currentTimeMillis();
        Date now = new Date(nowTimeMillis);
        //生成签名秘钥 key
        Key signingKey = generalKey();
        //添加构成JWT的参数
        Map<String, Object> headMap = new HashMap<>();
        /*
            Header
            {
              "alg": "HS256",
              "typ": "JWT"
            }
         */
        headMap.put("alg", signatureAlgorithm.getValue());
        headMap.put("typ", "JWT");
        JwtBuilder builder = Jwts.builder().setHeader(headMap)
                /*
                    Payload
                    {
                      "userId": "1234567890",
                      "userName": "John Doe",
                    }
                 */
                //加密后的客户编号
                .claim("userId", AESSecretUtil.encryptToStr(userId, SecretConstant.DATA_KEY))
                //客户名称
                .claim("userName", userName)
                //客户端浏览器信息
                .claim("userAgent", identities[0])
                //Signature
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (SecretConstant.EXPIRES_SECOND >= 0) {
            long expMillis = nowTimeMillis + SecretConstant.EXPIRES_SECOND;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate).setNotBefore(now);
        }
        return builder.compact();
    }

    /**
     * 解析JWT
     * @param jsonWebToken
     * @return 返回Claims对象
     */
    public static Claims parseJWT(String jsonWebToken) {
        Claims claims = null;
        try {
            if (!StringUtils.isEmpty(jsonWebToken)) {
                //解析jwt
                claims = Jwts.parser()
                        .setSigningKey(generalKey())
                        .parseClaimsJws(jsonWebToken)
                        .getBody();
            } else {
                LOGGER.warn("[JWTHelper]-json web token 为空");
            }
        } catch (Exception e) {
            LOGGER.error("[JWTHelper]-JWT解析异常：可能因为token已经超时或非法token");
        }
        return claims;
    }

    /**
     * 校验JWT是否有效
     * @param jsonWebToken - JWT
     * @return
     * 返回json字符串的demo:
     * {"freshToken":"A.B.C","userName":"Judy","userId":"123", "userAgent":"xxxx"}
     * freshToken-刷新后的jwt
     * userName-客户名称
     * userId-客户编号
     * userAgent-客户端浏览器信息
     */
    public static String validateToken(String jsonWebToken) throws JsonProcessingException {
        Map<String, Object> retMap = null;
        Claims claims = parseJWT(jsonWebToken);
        if (claims != null) {
            //解密客户编号
            String decryptUserId = AESSecretUtil.decryptToStr((String) claims.get("userId"), SecretConstant.DATA_KEY);
            retMap = new HashMap<>(16);
            //加密后的客户编号
            retMap.put("userId", decryptUserId);
            //客户名称
            retMap.put("userName", claims.get("userName"));
            //客户端浏览器信息
            retMap.put("userAgent", claims.get("userAgent"));
            //刷新JWT
            retMap.put("freshToken", generateJWT(decryptUserId, (String) claims.get("userName"), (String) claims.get("userAgent"), (String) claims.get("domainName")));
        } else {
            LOGGER.warn("[JWTHelper]-JWT解析出claims为空");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return retMap != null ? objectMapper.writeValueAsString(retMap) : null;
    }

    public static void main(String[] args) throws JsonProcessingException {
        String jsonWebKey = generateJWT("123", "Judy",
                "");
        System.out.println(jsonWebKey);
        Claims claims = parseJWT(jsonWebKey);
        System.out.println(claims);
        System.out.println(validateToken(jsonWebKey));
    }

}

