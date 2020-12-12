package online.heycm.auth.business.grant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户系统菜单
 * @author heycm
 * @since 2020-10-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("r_user_menu")
@ApiModel(value="UserMenuEntity对象", description="")
public class UserMenuEntity extends Model<UserMenuEntity> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "菜单id")
    private Integer menuId;

    @ApiModelProperty(value = "系统id")
    private Integer systemId;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
