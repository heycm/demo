package online.heycm.auth.enums;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-10-31 20:57
 */
public enum BaseEnum {

    // 增删改
    SAVE_OK("保存成功"),
    SAVE_ERROR("保存失败"),
    DEL_OK("删除成功"),
    DEL_ERROR( "删除失败"),
    SELECT_OK("查询成功"),
    SELECT_ERROR("查询失败"),

    // 登录
    LOGIN_OK("登录成功"),
    LOGIN_ERROR("用户名或密码错误"),
    LOGIN_EXPIRE("登录身份过期"),

    // 授权
    GRANT_OK("授权成功"),
    GRANT_ERROR("授权失败")
    ;


    private String msg;

    private BaseEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
