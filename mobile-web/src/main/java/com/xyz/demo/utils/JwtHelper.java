package com.xyz.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtHelper {

    // token秘钥，可定义
    public static final String SECRET = "SECRET";
    // token 过期时间: 30天
    public static final int calendarField = Calendar.DATE;
    public static final int calendarInterval = 30;

    //生成Token
    public static String createTokenById(Long user_id) throws Exception {
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // build token
        String token = JWT.create().withHeader(map) // header
                .withClaim("iss", "Service") // payload
                .withClaim("aud", "APP").withClaim("user_id", null == user_id ? null : user_id.toString())
                .withIssuedAt(iatDate) // sign time
                .withExpiresAt(expiresDate) // expire time
                .sign(Algorithm.HMAC256(SECRET)); // signature
        return token;
    }

    // 验证Token
    private static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) { // TokenExpiredException token 过期
            e.printStackTrace();
            return null;
        }
        return jwt.getClaims();
    }

    //Token解密获取数据
    public static int getIdByToken(String token) {
        Map<String, Claim> claims = verifyToken(token);
        if (claims == null) {
            return 0; // token 验证失败
        }
        Claim user_id_claim = claims.get("user_id");
        if (null == user_id_claim || StringUtils.isEmpty(user_id_claim.asString())) {
            return 0; // token 失效
        }
        return Integer.valueOf(user_id_claim.asString());
    }
}
