package online.heycm.auth.business.menu.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import online.heycm.auth.business.menu.entity.MenuEntity;

import java.io.Serializable;

/**
 * 菜单信息
 * @Author heycm@qq.com
 * @Date 2020-11-28 16:43
 */
@Data
public class MenuData implements Serializable {

    @ApiModelProperty(value = "菜单id")
    private Integer id;

    @ApiModelProperty(value = "系统id")
    private Integer systemId;

    @ApiModelProperty(value = "父菜单id")
    private Integer pid;

    @ApiModelProperty(value = "标题")
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

    public MenuData() {}

    public MenuData(MenuEntity entity) {
        id = entity.getId();
        systemId = entity.getSystemId();
        pid = entity.getPid();
        title = entity.getTitle();
        path = entity.getPath();
        icon = entity.getIcon();
        indez = entity.getIndez();
        type = entity.getType();
        enable = entity.getEnable();
        remark = entity.getRemark();
    }
}
