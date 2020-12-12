package online.heycm.auth.business.menu.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import online.heycm.auth.business.menu.entity.MenuEntity;
import online.heycm.auth.business.validation.group.Add;
import online.heycm.auth.business.validation.group.Edit;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * 菜单新增/修改
 * @Author heycm@qq.com
 * @Date 2020-11-28 16:28
 */
@Data
public class MenuModel implements Serializable {

    @Null(message = "菜单ID必须为空", groups = Add.class)
    @NotNull(message = "菜单ID不能为空", groups = Edit.class)
    private Integer id;

    @NotNull(message = "系统ID不能为空", groups = Add.class)
    private Integer systemId;

    @ApiModelProperty(value = "父菜单id")
    private Integer pid;

    @ApiModelProperty(value = "标题")
    @NotBlank(message = "菜单标题不能为空", groups = Add.class)
    private String title;

    @ApiModelProperty(value = "路由")
    private String path;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer indez;

    @ApiModelProperty(value = "类型：菜单集 0 菜单项 1，默认 0")
    private Integer type;

    @ApiModelProperty(value = "启用状态：0 启用 1 禁用，默认 0")
    private Integer enable;

    @ApiModelProperty(value = "备注")
    private String remark;

    public MenuEntity toEntity() {
        MenuEntity entity = new MenuEntity();
        entity.setId(id);
        entity.setSystemId(systemId);
        entity.setPid(pid);
        entity.setTitle(title);
        entity.setPath(path);
        entity.setIcon(icon);
        entity.setIndez(indez);
        entity.setType(type);
        entity.setEnable(enable);
        entity.setRemark(remark);
        return entity;
    }
}
