package online.heycm.auth.business.system.model;

import lombok.Data;
import online.heycm.auth.business.system.entity.SystemEntity;
import online.heycm.auth.business.validation.group.Add;
import online.heycm.auth.business.validation.group.Edit;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * 应用新增/编辑
 * @Author heycm@qq.com
 * @Date 2020-11-28 15:30
 */
@Data
public class SystemModel implements Serializable {

    @Null(message = "应用ID必须为空", groups = Add.class)
    @NotNull(message = "应用ID不能为空", groups = Edit.class)
    private Integer id;

    @NotBlank(message = "应用编码不能为空", groups = Add.class)
    private String code;

    @NotBlank(message = "应用名称不能为空", groups = Add.class)
    private String name;

    private String url;

    private Integer indez;

    private Integer enable;

    private String remark;

    public SystemEntity toEntity() {
        SystemEntity entity = new SystemEntity();
        entity.setId(id);
        entity.setCode(code);
        entity.setName(name);
        entity.setUrl(url);
        entity.setIndez(indez);
        entity.setEnable(enable);
        entity.setRemark(remark);
        return entity;
    }
}
