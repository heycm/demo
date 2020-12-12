package online.heycm.utils.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询接口入参模型
 *
 * @Author heycm@qq.com
 * @Date 2020-11-14 12:33
 */
@Data
public class PageParam<T> implements Serializable {

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页数量
     */
    private Integer size;

    /**
     * 其他查询参数
     */
    private T param;
}
