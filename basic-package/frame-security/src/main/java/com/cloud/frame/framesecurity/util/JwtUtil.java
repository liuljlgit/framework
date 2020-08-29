package com.cloud.frame.framesecurity.util;

import io.micrometer.core.instrument.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import java.util.Map;

/**
 * 解析jwtToken并获取信息
 * @author Liulj
 * @version v 0.1 2019/11/14 17:41
 */
@Slf4j
public class JwtUtil {

    /**
     * 解析JwtClaimsMap，得到具体的key值
     * @param accessToken
     * @param key
     * @return
     */
    public static Object getJwtClaimsVal(String accessToken,String key){
        Map<String, Object> jwtClaimsMap = getJwtClaimsMap(accessToken);
        return jwtClaimsMap.getOrDefault(key,null);
    }

    /**
     * 解析JwtClaimsMap
     * @param accessToken
     * @return
     */
    public static Map<String, Object> getJwtClaimsMap(String accessToken){
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Jwt jwt = JwtHelper.decode(accessToken);
        String claims = jwt.getClaims();
        return jsonParser.parseMap(claims);
    }

    /**
     * 校验并解析JwtClaimsMap
     * @param accessToken
     * @return
     */
    public static Map<String, Object> checkGetJwtClaimsMap(String accessToken){
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Resource resource = new ClassPathResource("public.txt");
        try {
            String publicKey = IOUtils.toString(resource.getInputStream());
            Jwt verifyJwt = JwtHelper.decodeAndVerify(accessToken,
                    new RsaVerifier(publicKey));
            String verifyJwtClaims = verifyJwt.getClaims();
            return jsonParser.parseMap(verifyJwtClaims);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
