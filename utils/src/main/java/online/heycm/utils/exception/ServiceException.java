package online.heycm.utils.exception;

import lombok.Getter;

/**
 * 自定义服务异常类
 *
 * @Author heycm@qq.com
 * @Date 2020-10-31 13:36
 */
@Getter
public class ServiceException extends RuntimeException {

    public static final String ERROR = "ERROR";
    public static final String WARN = "WARN";

    private String type;
    private Integer code;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(Integer code, String message, String type) {
        super(message);
        this.code = code;
        this.type = type;
    }

    public ServiceException(String message, String type) {
        super(message);
        this.type = type;
    }
}
