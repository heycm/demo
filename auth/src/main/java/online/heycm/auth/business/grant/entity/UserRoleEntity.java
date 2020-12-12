package online.heycm.auth.business.grant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户角色
 * @author heycm
 * @since 2020-10-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("r_user_role")
@ApiModel(value="UserRoleEntity对象", description="")
public class UserRoleEntity extends Model<UserRoleEntity> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
