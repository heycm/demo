package online.heycm.auth.business.grant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 角色系统菜单
 * @author heycm
 * @since 2020-10-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("r_role_menu")
@ApiModel(value="RoleMenuEntity对象", description="")
public class RoleMenuEntity extends Model<RoleMenuEntity> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @ApiModelProperty(value = "菜单id")
    private Integer menuId;

    @ApiModelProperty(value = "系统id")
    private Integer systemId;


    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
