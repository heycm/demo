package online.heycm.auth.business.role.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import online.heycm.auth.business.role.entity.RoleEntity;
import online.heycm.auth.business.validation.group.Add;
import online.heycm.auth.business.validation.group.Edit;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * 角色新增、修改校验
 * @Author heycm@qq.com
 * @Date 2020-11-15 16:59
 */
@Data
public class RoleModel implements Serializable {

    @ApiModelProperty(value = "角色id")
    @Null(message = "角色ID必须为空", groups = Add.class)
    @NotNull(message = "角色ID不能为空", groups = Edit.class)
    private Integer id;

    @ApiModelProperty(value = "英文名称")
    @NotBlank(message = "角色英文名称不能为空", groups = Add.class)
    private String name;

    @ApiModelProperty(value = "中文名称")
    @NotBlank(message = "角色中文名称不能为空", groups = Add.class)
    private String nameZh;

    @ApiModelProperty(value = "类型（保留字段）")
    private Integer type;

    @ApiModelProperty(value = "启用状态：0 启用 1 禁用，默认 0")
    @NotNull(message = "角色启用状态不能为空", groups = Add.class)
    private Integer enable;

    @ApiModelProperty(value = "备注")
    private String remark;

    public RoleEntity toEntity() {
        RoleEntity entity = new RoleEntity();
        entity.setId(id);
        entity.setName(name);
        entity.setNameZh(nameZh);
        entity.setType(type);
        entity.setEnable(enable);
        entity.setRemark(remark);
        return entity;
    }
}
