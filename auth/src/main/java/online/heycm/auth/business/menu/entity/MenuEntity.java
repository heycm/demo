package online.heycm.auth.business.menu.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单实体
 * @author heycm
 * @since 2020-10-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_menu")
@ApiModel(value="MenuEntity对象", description="")
public class MenuEntity extends Model<MenuEntity> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "菜单id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "系统id")
    private Integer systemId;

    @ApiModelProperty(value = "父菜单id")
    private Integer pid;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "路由")
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String path;

    @ApiModelProperty(value = "图标")
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer indez;

    @ApiModelProperty(value = "类型：菜单集 0 菜单项 1，默认 0")
    private Integer type;

    @ApiModelProperty(value = "启用状态：0 启用 1 禁用，默认 0")
    private Integer enable;

    @ApiModelProperty(value = "备注")
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String remark;

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
