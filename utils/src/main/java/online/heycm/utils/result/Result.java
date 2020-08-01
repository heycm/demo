package online.heycm.utils.result;

/**
 * 统一返回对象构造工具
 *
 * @Author heycm@qq.com
 * @Date 2020-08-01 14:26
 */
public class Result {

    public static <T>ResModel apiRes(boolean isOk, T data, String msg) {
        return ResModel.builder()
                .ok(isOk)
                .data(isOk ? data : null)
                .msg(isOk ? null : msg)
                .build();
    }

    public static ResModel ok() {
        return ResModel.builder()
                .ok(true)
                .build();
    }

    public static <T>ResModel ok(T data) {
        if (data instanceof ResEnum) {
            return ResModel.builder()
                    .ok(true)
                    .code(((ResEnum) data).getCode())
                    .data(((ResEnum) data).getMsg())
                    .build();
        }
        return ResModel.builder()
                .ok(true)
                .data(data)
                .build();
    }

    public static ResModel error(String msg) {
        return ResModel.builder()
                .ok(false)
                .msg(msg)
                .build();
    }

    public static ResModel error(ResEnum resEnum) {
        return ResModel.builder()
                .ok(false)
                .code(resEnum.getCode())
                .msg(resEnum.getMsg())
                .build();
    }
}