package online.heycm.utils.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 基本接口返回模型
 *
 * @Author heycm@qq.com
 * @Date 2020-11-14 11:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult implements Serializable {

    /**
     * 是否成功：true 是 false 否
     */
    private boolean ok;

    /**
     * 接口消息
     */
    private String msg;

}
