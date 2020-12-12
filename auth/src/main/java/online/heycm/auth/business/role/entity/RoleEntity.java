package online.heycm.auth.business.role.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色实体
 * @author heycm
 * @since 2020-10-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role")
@ApiModel(value="RoleEntity对象", description="")
public class RoleEntity extends Model<RoleEntity> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色id")
    @TableId(value = "id", type = IdType.AUTO)
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
    @TableField(updateStrategy = FieldStrategy.IGNORED)
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
