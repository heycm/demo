package online.heycm.utils.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回对象模型
 *
 * @Author heycm@qq.com
 * @Date 2020-08-01 14:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResModel<T> {
    private boolean ok;
    private String code;
    private String msg;
    private T data;
}