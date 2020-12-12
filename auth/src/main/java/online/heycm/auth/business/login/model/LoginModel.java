package online.heycm.auth.business.login.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户登录、修改密码模型
 * @Author heycm@qq.com
 * @Date 2020-11-01 18:35
 */
@Data
public class LoginModel implements Serializable {

    @NotBlank(message = "账户不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

}
