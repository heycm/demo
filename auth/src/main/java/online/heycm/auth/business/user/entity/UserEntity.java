package online.heycm.auth.business.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体
 * @author heycm
 * @since 2020-10-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
@ApiModel(value="UserEntity对象", description="")
public class UserEntity extends Model<UserEntity> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "账户名")
    private String username;

    @ApiModelProperty(value = "密码密文")
    private String password;

    @ApiModelProperty(value = "加密盐值")
    private String salt;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "用户类型（保留字段）")
    private Integer type;

    @ApiModelProperty(value = "启用状态：0 启用 1 禁用，默认0")
    private Integer enable;

    @ApiModelProperty(value = "创建人id")
    @TableField(fill = FieldFill.INSERT)
    private Integer createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createAt;

    @ApiModelProperty(value = "最新修改人id")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer modifyBy;

    @ApiModelProperty(value = "最新修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifyAt;

    @ApiModelProperty(value = "逻辑删除：0 未删除 1 已删除")
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
