package online.heycm.auth.business.user.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 修改密码
 * @Author heycm@qq.com
 * @Date 2020-12-12 12:17
 */
@Data
public class ChangePwd implements Serializable {

    @NotBlank(message = "原密码不能为空")
    private String oldPwd;

    @NotBlank(message = "新密码不能为空")
    private String newPwd;
}
