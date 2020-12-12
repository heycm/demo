package online.heycm.auth.business.grant.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色菜单授权
 * @Author heycm@qq.com
 * @Date 2020-11-28 18:17
 */
@Data
public class RoleMenuModel implements Serializable {

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @ApiModelProperty(value = "菜单id集合")
    private List<Integer> menuIds;

    @ApiModelProperty(value = "系统id")
    private Integer systemId;
}
