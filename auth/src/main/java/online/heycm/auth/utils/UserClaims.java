package online.heycm.auth.utils;

import lombok.Data;
import online.heycm.utils.jwt.BaseClaims;

/**
 * 签发JWT时携带的信息
 * @Author heycm@qq.com
 * @Date 2020-10-31 20:01
 */
@Data
public class UserClaims extends BaseClaims {

    private String roleId;

}
