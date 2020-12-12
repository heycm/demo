package online.heycm.auth.business.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

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
@TableName("sys_system")
@ApiModel(value="SystemEntity对象", description="")
public class SystemEntity extends Model<SystemEntity> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "系统id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "唯一编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "地址")
    private String url;

    @ApiModelProperty(value = "排序")
    private Integer indez;

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
