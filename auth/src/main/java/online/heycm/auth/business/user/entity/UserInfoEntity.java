package online.heycm.auth.business.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author heycm
 * @since 2020-10-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user_info")
@ApiModel(value="UserInfoEntity对象", description="")
public class UserInfoEntity extends Model<UserInfoEntity> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户信息id（用户id）")
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String realname;

    @ApiModelProperty(value = "性别：0 男 1 女，默认 0")
    private Integer sex;

    @ApiModelProperty(value = "联系方式")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
