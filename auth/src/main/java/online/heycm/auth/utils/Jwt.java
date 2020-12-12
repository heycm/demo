package online.heycm.auth.utils;

import online.heycm.utils.jwt.JwtUtils;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-10-31 19:58
 */
public class Jwt {

    /**
     * token在请求头中的名称
     */
    public static final String HEADER = "Authorization";

    /**
     * 签名私钥
     */
    private static final String PRIVATE_KEY = "askdjlHLOIHdjaqwoaps484a";
    /**
     * 有效期(秒)
     */
    private static final long EXPIRE = 60 * 60L;

    /**
     * 签发JWT
     * @param claims 签发携带信息
     * @return
     */
    public static String generate(UserClaims claims) {
        return JwtUtils.generate(PRIVATE_KEY, EXPIRE, claims);
    }

    /**
     * 解析JWT
     * @param token JWT
     * @return
     */
    public static UserClaims parse(String token) {
        return JwtUtils.parse(PRIVATE_KEY, token, UserClaims.class);
    }

    /**
     * 校验JWT
     * @param token JWT
     * @return
     */
    public static boolean verify(String token) {
        try {
            return JwtUtils.verify(PRIVATE_KEY, token);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取JWT签发时间戳(秒)
     * @param token JWT
     * @return
     */
    public static Integer getIat(String token) {
        return JwtUtils.getIat(PRIVATE_KEY, token);
    }

    /**
     * 获取JWT过期时间戳(秒)
     * @param token JWT
     * @return
     */
    public static Integer getExp(String token) {
        return JwtUtils.getExp(PRIVATE_KEY, token);
    }

    /**
     * 从Token获取用户ID
     * @param token JWT
     * @return
     */
    public static Integer getUserId(String token) {
        return Integer.parseInt(parse(token).getId());
    }

    /**
     * 从Token获取用户账户
     * @param token JWT
     * @return
     */
    public static String getUsername(String token) {
        return parse(token).getUsername();
    }
}
