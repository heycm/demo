package online.heycm.mybatisplus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Mybatis Plus 字段自动注入配置
 *
 * @Author heycm@qq.com
 * @Date 2020-08-01 23:15
 */
@Component
public class AutoFillHandler implements MetaObjectHandler {

    @Value("${jwt.token.name}")
    private String jwtTokenName;

    @Autowired
    HttpServletRequest request;

    @Override
    public void insertFill(MetaObject metaObject) {

        //判断表里是否有createTime的方法,如果有可以再去加自动填充，因为我们所有表都有，所以不加判断
        //boolean hasSetter = metaObject.hasSetter("createTime");
        setInsertFieldValByName("createTime", new Date(), metaObject);
        setInsertFieldValByName("modifyTime", new Date(), metaObject);
        setInsertFieldValByName("isDeleted", 0, metaObject);
        setInsertFieldValByName("createUser", getIdByToken(), metaObject);
        setInsertFieldValByName("modifyUser", getIdByToken(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 判断是否已设置修改人，若已设置，此处不再填充
        Object modifyUser = getFieldValByName("modifyUser", metaObject);
        if(modifyUser == null) {
            setUpdateFieldValByName("modifyUser", getIdByToken(), metaObject);
        }
        setUpdateFieldValByName("modifyTime", new Date(), metaObject);
    }

    private Integer getIdByToken() {
        String token = request.getHeader(jwtTokenName);
        if (token == null) {
            return null;
        }
        // TODO 解析JWT，获取JTW中的用户ID
        return 1;
    }
}
