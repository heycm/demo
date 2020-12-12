package online.heycm.utils.result;

import java.util.List;

/**
 * 接口返回对象构建工具
 *
 * @Author heycm@qq.com
 * @Date 2020-11-14 12:26
 */
public class Result {

    /**
     * 基本返回(成功状态)
     * @param msg 接口消息
     */
    public static ApiResult ok(String msg) {
        return new ApiResult(true, msg);
    }

    /**
     * 基本返回(失败状态)
     * @param msg 接口消息
     */
    public static ApiResult error(String msg) {
        return new ApiResult(false, msg);
    }

    /**
     * 基本返回
     * @param ok 是否成功：true 是 false 否
     * @param msg 接口消息
     */
    public static ApiResult api(boolean ok, String msg) {
        return new ApiResult(ok, msg);
    }

    /**
     * 基本返回
     * @param ok 是否成功：true 是 false 否
     * @param okMsg 成功消息
     * @param errMsg 失败消息
     */
    public static ApiResult api(boolean ok, String okMsg, String errMsg) {
        return new ApiResult(ok, ok ? okMsg : errMsg);
    }

    /**
     * 数据返回
     * @param data 返回数据
     * @param <T> 返回数据泛型
     */
    public static <T> DataResult<T> data(T data) {
        DataResult<T> result = new DataResult<>(data);
        result.setOk(true);
        return result;
    }

    /**
     * 数据返回
     * @param msg 接口消息
     * @param data 返回数据
     * @param <T> 返回数据泛型
     */
    public static <T> DataResult<T> data(String msg, T data) {
        DataResult<T> result = new DataResult<>(data);
        result.setOk(true);
        result.setMsg(msg);
        return result;
    }

    /**
     * 分页数据返回
     * @param pages 总页数
     * @param page 当前页
     * @param size 每页数据量
     * @param total 总数据量
     * @param data 返回数据
     * @param <T> 返回数据泛型
     */
    public static <T> PageResult<T> page(long pages, long page, long size, long total, List<T> data) {
        PageResult<T> result = new PageResult<>(pages, page, size, total, data);
        result.setOk(true);
        return result;
    }

    /**
     * 分页数据返回
     * @param msg 接口消息
     * @param pages 总页数
     * @param page 当前页
     * @param size 每页数据量
     * @param total 总数据量
     * @param data 返回数据
     * @param <T> 返回数据泛型
     */
    public static <T> PageResult<T> page(String msg, long pages, long page, long size, long total, List<T> data) {
        PageResult<T> result = new PageResult<>(pages, page, size, total, data);
        result.setOk(true);
        result.setMsg(msg);
        return result;
    }
}
