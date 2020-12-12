package online.heycm.auth.business.login.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import online.heycm.auth.business.grant.entity.UserRoleEntity;
import online.heycm.auth.business.grant.service.impl.UserRoleServiceImpl;
import online.heycm.auth.business.login.model.LoginData;
import online.heycm.auth.business.login.model.LoginModel;
import online.heycm.auth.business.user.entity.UserEntity;
import online.heycm.auth.business.user.service.impl.UserServiceImpl;
import online.heycm.auth.utils.Jwt;
import online.heycm.auth.utils.PasswordUtils;
import online.heycm.auth.utils.UserClaims;
import online.heycm.utils.exception.ServiceException;
import online.heycm.utils.result.DataResult;
import online.heycm.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 登录服务实现类
 *
 * @Author heycm@qq.com
 * @Date 2020-11-01 19:00
 */
@Service
public class LoginServiceImpl {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserRoleServiceImpl userRoleService;

    /**
     * 登录
     * @param model
     * @return
     */
    public DataResult<LoginData> login(LoginModel model) {
        UserEntity entity = userService.getByUsername(model.getUsername());
        if (entity == null) {
            throw new ServiceException("用户名或密码错误");
        }
        String encrypt = PasswordUtils.encrypt(model.getPassword(), entity.getSalt());
        if (!entity.getPassword().equals(encrypt)) {
            throw new ServiceException("用户名或密码错误");
        }
        if (!entity.getEnable().equals(0)) {
            throw new ServiceException("您的账户已被锁定");
        }
        LoginData data = new LoginData(entity.getId(), entity.getUsername(), entity.getNickname(), issueJwt(entity));
        return Result.data("登录成功", data);
    }

    /**
     * 单点登录
     * @param userId
     * @return
     */
    public DataResult<LoginData> singleSignOn(Integer userId) {
        UserEntity entity = userService.getById(userId);
        LoginData data = new LoginData(entity.getId(), entity.getUsername(), entity.getNickname(), issueJwt(entity));
        return Result.data("登录成功", data);
    }

    /**
     * 签发jwt
     * @param entity
     * @return
     */
    private String issueJwt(UserEntity entity) {
        UserClaims claims = new UserClaims();
        claims.setId(String.valueOf(entity.getId()));
        claims.setUsername(entity.getUsername());
        UserRoleEntity one = userRoleService.getOne(new QueryWrapper<UserRoleEntity>().lambda().eq(UserRoleEntity::getUserId, entity.getId()));
        claims.setRoleId(one == null ? null : String.valueOf(one.getRoleId()));
        return Jwt.generate(claims);
    }
}
