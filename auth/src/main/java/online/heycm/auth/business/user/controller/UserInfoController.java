package online.heycm.auth.business.user.controller;


import io.swagger.annotations.Api;
import online.heycm.auth.business.user.service.impl.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息API
 * @author heycm
 * @since 2020-10-31
 */
@RestController
@RequestMapping("/userInfo")
@Api(tags = "用户信息")
public class UserInfoController {

    @Autowired
    UserInfoServiceImpl service;


}

