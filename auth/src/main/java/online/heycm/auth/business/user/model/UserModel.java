package online.heycm.auth.business.user.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import online.heycm.auth.business.user.entity.UserEntity;
import online.heycm.auth.business.validation.group.Add;
import online.heycm.auth.business.validation.group.Edit;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-11-01 12:26
 */
@Data
public class UserModel implements Serializable {

    @ApiModelProperty(value = "用户id")
    @Null(message = "用户ID必须为空", groups = Add.class)
    @NotNull(message = "用户ID不能为空", groups = Edit.class)
    private Integer id;

    @ApiModelProperty(value = "账户名")
    @NotBlank(message = "用户名不能为空", groups = {Add.class})
    private String username;

    @ApiModelProperty(value = "原密码")
    private String password;

    @ApiModelProperty(value = "新密码")
    private String newPassword;

    @ApiModelProperty(value = "昵称")
    @NotBlank(message = "用户昵称不能为空", groups = Add.class)
    private String nickname;

    @ApiModelProperty(value = "启用状态：0 启用 1 禁用，默认0")
    @NotNull(message = "状态不能为空", groups = Add.class)
    private Integer enable;

    @ApiModelProperty(value = "创建时间")
    private Date createAt;

    public UserEntity toEntity() {
        UserEntity entity = new UserEntity();
        entity.setId(id);
        entity.setUsername(username);
        entity.setPassword(password);
        entity.setNickname(nickname);
        entity.setEnable(enable);
        entity.setCreateAt(createAt);
        return entity;
    }
}
