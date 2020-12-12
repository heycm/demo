package online.heycm.utils.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询接口返回模型
 *
 * @Author heycm@qq.com
 * @Date 2020-11-14 11:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataResult<T> extends ApiResult {

    /**
     * 接口数据
     */
    private T data;

}
