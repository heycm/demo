package online.heycm.auth.config.mp;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import online.heycm.auth.utils.Jwt;
import online.heycm.utils.exception.ServiceException;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * MP自动注入字段配置
 *
 * @Author heycm@qq.com
 * @Date 2020-10-31 19:39
 */
@Component
public class AutoFillHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        boolean isDeleted = metaObject.hasSetter("isDeleted");
        boolean createBy = metaObject.hasSetter("createBy");
        boolean createAt = metaObject.hasSetter("createAt");
        boolean modifyBy = metaObject.hasSetter("createAt");
        boolean modifyAt = metaObject.hasSetter("createAt");
        Date date = null;
        Integer userId = null;
        if (createBy || modifyBy) {
            date = new Date();
        }
        if (createAt || modifyAt) {
            userId = getUserId();
        }
        if (isDeleted) {
            metaObject.setValue("isDeleted", 0);
        }
        if (createBy) {
            metaObject.setValue("createBy", userId);
        }
        if (createAt) {
            metaObject.setValue("createAt", date);
        }
        if (modifyBy) {
            metaObject.setValue("modifyBy", userId);
        }
        if (modifyAt) {
            metaObject.setValue("modifyAt", date);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("modifyBy", getUserId());
        metaObject.setValue("modifyAt", new Date());
    }

    private Integer getUserId() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader(Jwt.HEADER);
        try {
            return Jwt.getUserId(token);
        } catch (Exception e) {
            throw new RuntimeException("登录身份过期");
        }
    }
}
