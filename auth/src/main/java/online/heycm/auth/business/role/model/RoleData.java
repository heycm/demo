package online.heycm.auth.business.role.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import online.heycm.auth.business.role.entity.RoleEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色信息
 * @Author heycm@qq.com
 * @Date 2020-11-15 17:44
 */
@Data
public class RoleData implements Serializable {

    @ApiModelProperty(value = "角色id")
    private Integer id;

    @ApiModelProperty(value = "英文名称")
    private String name;

    @ApiModelProperty(value = "中文名称")
    private String nameZh;

    @ApiModelProperty(value = "类型（保留字段）")
    private Integer type;

    @ApiModelProperty(value = "启用状态：0 启用 1 禁用，默认 0")
    private Integer enable;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date createAt;

    public RoleData() {}

    public RoleData(RoleEntity entity) {
        id = entity.getId();
        name = entity.getName();
        nameZh = entity.getNameZh();
        type = entity.getType();
        enable = entity.getEnable();
        remark = entity.getRemark();
        createAt = entity.getCreateAt();
    }
}
