package online.heycm.auth.business.login.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录成功返回模型
 * @Author heycm@qq.com
 * @Date 2020-11-15 16:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginData {

    private Integer id;

    private String username;

    private String nickname;

    private String token;

}
