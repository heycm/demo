package online.heycm.auth.business.login.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.heycm.auth.base.BaseController;
import online.heycm.auth.business.login.model.LoginData;
import online.heycm.auth.business.login.model.LoginModel;
import online.heycm.auth.business.login.service.LoginServiceImpl;
import online.heycm.utils.exception.ServiceException;
import online.heycm.utils.result.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录接口
 *
 * @Author heycm@qq.com
 * @Date 2020-11-01 18:34
 */
@RestController
@Api(tags = "登录接口")
public class LoginController extends BaseController {

    @Autowired
    LoginServiceImpl loginService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public DataResult<LoginData> login(@RequestBody @Validated LoginModel model) {
        return loginService.login(model);
    }

    @GetMapping("/sso")
    @ApiOperation("单点登录")
    public DataResult<LoginData> single() {
        Integer userId = null;
        try {
            userId = getUserId();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        return loginService.singleSignOn(userId);
    }
}
