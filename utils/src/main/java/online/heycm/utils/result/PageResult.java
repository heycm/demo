package online.heycm.utils.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页查询接口结果模型
 *
 * @Author heycm@qq.com
 * @Date 2020-11-14 11:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> extends ApiResult{

    /**
     * 总页数
     */
    private long pages;

    /**
     * 当前页
     */
    private long page;

    /**
     * 每页数据量
     */
    private long size;

    /**
     * 总数据量
     */
    private long total;

    /**
     * 当前分页数据
     */
    private List<T> data;

}
