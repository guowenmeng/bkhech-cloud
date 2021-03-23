package com.bkhech.cloud.commons.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

/**
 * jwt 工具类
 * @author guowm
 * @date 2021/3/22
 */
public class JwtUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

    public static final String KEY = "022bdc63c3c5a45879ee6581508b9d03adfec4a4658c0ab3d722e50c91a351c42c231cf43bb8f86998202bd301ec52239a74fc0c9a9aeccce604743367c9646b";

    /**
     * 由字符串生成加密key
     * @return
     */
    public static SecretKey generalKey(){
        byte[] base64EncodedKeyBytes = Base64.getDecoder().decode(KEY);
        SecretKeySpec key = new SecretKeySpec(base64EncodedKeyBytes, 0, base64EncodedKeyBytes.length, "AES");
        return key;
    }

    /**
     * 创建jwt
     * @param id
     * @param issuer
     * @param subject
     * @param ttlMillis
     * @return jwt token
     */
    public static String createJWT(String id, String issuer, String subject, long ttlMillis) {

        // 指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        Map<String, Object> claims = new HashMap<>(16);
        claims.put("uid", "123456");

        // 生成签名的时候使用的秘钥secret，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。
        // 一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        SecretKey key = generalKey();

        // 下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder() // 这里其实就是new一个JwtBuilder，设置jwt的body
                .setClaims(claims)          // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setId(id)                  // 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(now)           // iat: jwt的签发时间
                .setIssuer(issuer)          // issuer：jwt签发人
                .setSubject(subject)        // sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .signWith(signatureAlgorithm, key); // 设置签名使用的签名算法和签名使用的秘钥

        // 设置过期时间
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key = generalKey();
        Claims claims = Jwts.parser()  //得到DefaultJwtParser
                .setSigningKey(key)    //设置签名的秘钥
                .parseClaimsJws(jwt)  //设置需要解析的jwt
                .getBody();
        return claims;
    }

    /**
     * 检查token
     * @return
     */
    public static boolean checkToken(String jwtToken, ObjectMapper objectMapper) throws Exception {
        Claims claims = JwtUtil.parseJWT(jwtToken);
        String subject = claims.getSubject();
        JwtModel jwtModel = objectMapper.readValue(subject, JwtModel.class);
        /**
         * TODO 对jwt里面的用户信息做判断，根据自己的业务编写
         */

        return true;
    }

    public static void main(String[] args) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        System.out.println("----------" + signatureAlgorithm.getJcaName());

        ObjectMapper objectMapper = new ObjectMapper();

        String id = "123456";
        String issuer = "guowm";

        JwtModel jwtModel = new JwtModel();
        jwtModel.setUserName("bkhech");
        jwtModel.setRoleIdList(Arrays.asList("admin"));
        long ttlTimeMills = 60000;
        String jwt = JwtUtil.createJWT(id, issuer, objectMapper.writeValueAsString(jwtModel), ttlTimeMills);
        System.out.println(jwt);

        String jwtToken ="eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIxMjM0NTYiLCJzdWIiOiJ7XCJ1c2VyTmFtZVwiOlwiYmtoZWNoXCIsXCJyb2xlSWRMaXN0XCI6W1wiYWRtaW5cIl19IiwidXNlcl9uYW1lIjoiYWRtaW4iLCJuaWNrX25hbWUiOiJYLXJhcGlkbyIsImlzcyI6Imd1b3dtIiwiZXhwIjoxNjE2MTM4NDMzLCJpYXQiOjE2MTYxMzgzNzMsImp0aSI6IjEyMzQ1NiJ9.QoXam0m9c_othLOiskGcWK1O3pfVyUi9MlzrMoEKexw";
        boolean b = JwtUtil.checkToken(jwtToken, objectMapper);
        System.out.println(b);
    }

}
