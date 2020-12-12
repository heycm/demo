package online.heycm.auth.business.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 树形结构数据模型
 * @Author heycm@qq.com
 * @Date 2020-11-28 17:00
 */
@Data
public class TreeModel<T> implements Serializable {

    @ApiModelProperty(value = "整个树范围内的所有节点的 key 值不能重复且不为空！")
    private String key;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "设置为叶子节点")
    private Boolean isLeaf;

    @ApiModelProperty(value = "设置节点是否展开(叶子节点无效)")
    private Boolean expanded;

    @ApiModelProperty(value = "设置是否禁用节点(不可进行任何操作)")
    private Boolean disabled;

    @ApiModelProperty(value = "子节点")
    private List<TreeModel<T>> children;

    @ApiModelProperty(value = "源数据")
    private T origin;

}
