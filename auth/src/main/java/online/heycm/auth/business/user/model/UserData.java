package online.heycm.auth.business.user.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import online.heycm.auth.business.user.entity.UserEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 * @Author heycm@qq.com
 * @Date 2020-11-14 13:54
 */
@Data
public class UserData implements Serializable {

    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "账户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "用户类型（保留字段）")
    private Integer type;

    @ApiModelProperty(value = "启用状态：0 启用 1 禁用，默认0")
    private Integer enable;

    @ApiModelProperty(value = "创建时间")
    private Date createAt;

    public UserData() {}

    public UserData(UserEntity entity) {
        id = entity.getId();
        username = entity.getUsername();
        nickname = entity.getNickname();
        type = entity.getType();
        enable = entity.getEnable();
        createAt = entity.getCreateAt();
    }

}
