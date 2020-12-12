package online.heycm.utils.jwt;

import lombok.Data;

import java.io.Serializable;

/**
 * 基本Claims
 *
 * @Author heycm@qq.com
 * @Date 2020-10-31 14:02
 */
@Data
public class BaseClaims implements Serializable {
    /**
     * 签发时间戳(秒)
     */
    private Integer iat;
    /**
     * 过期时间戳(秒)
     */
    private Integer exp;
    /**
     * 用户ID
     */
    private String id;
    /**
     * 用户名称
     */
    private String username;
}
