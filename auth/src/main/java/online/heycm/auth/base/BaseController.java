package online.heycm.auth.base;

import cn.hutool.core.util.StrUtil;
import online.heycm.auth.utils.Jwt;
import online.heycm.utils.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-10-31 19:42
 */
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    /**
     * 获取token中用户id
     * @return
     */
    protected Integer getUserId() {
        try {
            String token = request.getHeader(Jwt.HEADER);
            return Jwt.getUserId(token);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * 获取token中用户角色ID
     * @return
     */
    protected Integer getRoleId() {
        try {
            String token = request.getHeader(Jwt.HEADER);
            String roleId = Jwt.parse(token).getRoleId();
            if (StrUtil.isBlank(roleId)) {
                throw new ServiceException("未授权角色");
            }
            return Integer.parseInt(roleId);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
