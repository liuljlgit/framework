package com.cloud.frame.framesecurity.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

import java.util.Map;

/**
 * 解析jwtToken并获取信息
 * @author Liulj
 * @version v 0.1 2019/11/14 17:41
 */
@Slf4j
public class JwtUtil {

    public static Object getTokenAdditionInfoValue(String accessToken,Object key){
        Map tokenAdditionInfoMap = getTokenAdditionInfoMap(accessToken);
        return tokenAdditionInfoMap.get(key);
    }

    public static Map getTokenAdditionInfoMap(String accessToken){
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Jwt jwt = JwtHelper.decode(accessToken);
        String claims = jwt.getClaims();
        return jsonParser.parseMap(claims);
    }

//    public static void getCheckJwtToken(String accessToken){
//        JsonParser jsonParser = JsonParserFactory.getJsonParser();
//        KeyPair keyPair = new KeyStoreKeyFactory(
//                new ClassPathResource("keystore.jks"), "123456".toCharArray())
//                .getKeyPair("o2jks");
//        Jwt decodeAndVerifyJwt = JwtHelper.decodeAndVerify(accessToken,
//                new RsaVerifier((RSAPublicKey) keyPair.getPublic(),
//                        "SHA256withRSA"));
//        Jwt decodeJwt = JwtHelper.decode(accessToken);
//        String decodeAndVerifyJwtClaims = decodeAndVerifyJwt.getClaims();
//        String decodeJwtClaims = decodeJwt.getClaims();
//        Map<String, Object> decodeAndVerifyJwtClaimsMap = jsonParser.parseMap(decodeAndVerifyJwtClaims);
//        Map<String, Object> decodeJwtClaimsMap = jsonParser.parseMap(decodeJwtClaims);
//    }

}
