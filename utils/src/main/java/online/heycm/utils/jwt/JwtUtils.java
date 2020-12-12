package online.heycm.utils.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.lang.reflect.Field;
import java.util.*;

/**
 * JWT签发、解析工具类
 * @Author heycm@qq.com
 * @Date 2020-10-31 13:55
 */
@Slf4j
public class JwtUtils {

    /**
     * 传入BaseClaims或其子类，签发JWT
     * @param privateKey 加密私钥
     * @param expire 有效时间(秒)
     * @param claims 携带信息
     * @return
     * @throws Exception
     */
    public static <T extends BaseClaims> String generate(String privateKey, long expire, T claims) {
        try {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(privateKey);
            new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
            JwtBuilder jwtBuilder = Jwts.builder()
                    .setHeaderParam("type", "Jwt")
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expire * 1000L))
                    .signWith(signatureAlgorithm, apiKeySecretBytes);
            List<Field> fields = new ArrayList<>();
            Class<?> clasz = claims.getClass();
            while (clasz != null) {
                fields.addAll(Arrays.asList(clasz.getDeclaredFields()));
                clasz = clasz.getSuperclass();
            }
            for (Field f : fields) {
                f.setAccessible(true);
                if (f.get(claims) != null) {
                    jwtBuilder.claim(f.getName(), f.get(claims).toString());
                }
            }
            return jwtBuilder.compact();
        } catch (Exception e) {
            log.error("[开始][签发JWT异常][结束]", e);
            throw new RuntimeException("JWT签发异常");
        }
    }

    /**
     * 解析JWT
     * @param privateKey 加密私钥
     * @param token JWT
     * @return
     */
    public static Claims parse(String privateKey, String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(privateKey))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.error("[开始][JWT已过期][结束]", e);
            throw new RuntimeException("JWT已过期");
        } catch (UnsupportedJwtException e) {
            log.error("[开始][不支持此JWT][结束]", e);
            throw new RuntimeException("不支持此JWT");
        } catch (MalformedJwtException e) {
            log.error("[开始][JWT格式异常][结束]", e);
            throw new RuntimeException("JWT格式异常");
        } catch (SignatureException e) {
            log.error("[开始][JWT签名异常][结束]", e);
            throw new RuntimeException("JWT签名异常");
        } catch (IllegalArgumentException e) {
            log.error("[开始][非法JWT][结束]", e);
            throw new RuntimeException("非法JWT");
        }
    }

    /**
     * 解析，返回BaseClaims或其子类
     * @param privateKey 加密私钥
     * @param token JWT
     * @param clasz BaseClaims或其子类
     * @return
     */
    public static <T extends BaseClaims> T parse(String privateKey, String token, Class<T> clasz) {
        Claims jwtClaims = parse(privateKey, token);
        try {
            T claims = clasz.newInstance();
            List<Field> fields = new ArrayList<>();
            while (clasz != null) {
                fields.addAll(Arrays.asList(clasz.getDeclaredFields()));
                clasz = (Class<T>) clasz.getSuperclass();
            }
            for(Field f :fields) {
                f.setAccessible(true);
                f.set(claims, jwtClaims.get(f.getName()));
            }
            return claims;
        } catch (Exception e) {
            log.error("[开始][JWT反射取值异常][结束]", e);
            throw new RuntimeException("JWT反射取值异常");
        }
    }

    /**
     * 校验JWT
     * @param privateKey 私钥
     * @param token JWT
     * @return
     */
    public static boolean verify(String privateKey, String token) {
        parse(privateKey, token);
        return true;
    }

    /**
     * 获取JWT签发时间戳(秒)
     * @param token JWT
     * @return
     */
    public static Integer getIat(String privateKey, String token) {
        return parse(privateKey, token, BaseClaims.class).getIat();
    }

    /**
     * 获取JWT过期时间戳(秒)
     * @param token JWT
     * @return
     */
    public static Integer getExp(String privateKey, String token) {
        return parse(privateKey, token, BaseClaims.class).getExp();
    }
}
