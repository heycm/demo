package online.heycm.auth.business.system.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import online.heycm.auth.business.system.entity.SystemEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 应用信息
 * @Author heycm@qq.com
 * @Date 2020-11-28 15:44
 */
@Data
public class SystemData implements Serializable {

    @ApiModelProperty(value = "系统id")
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
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date createAt;

    public SystemData() {}

    public SystemData(SystemEntity entity) {
        id = entity.getId();
        code = entity.getCode();
        name = entity.getName();
        url = entity.getUrl();
        indez = entity.getIndez();
        enable = entity.getEnable();
        remark = entity.getRemark();
        createAt = entity.getCreateAt();
    }
}
