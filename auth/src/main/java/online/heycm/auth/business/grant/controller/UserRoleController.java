package online.heycm.auth.business.grant.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.heycm.auth.business.grant.entity.UserRoleEntity;
import online.heycm.auth.business.grant.service.impl.UserRoleServiceImpl;
import online.heycm.utils.result.ApiResult;
import online.heycm.utils.result.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户角色API
 * @author heycm
 * @since 2020-10-31
 */
@RestController
@RequestMapping("/user/role")
@Api(tags = "授权 - 用户角色")
public class UserRoleController {

    @Autowired
    private UserRoleServiceImpl service;

    @PostMapping
    @ApiOperation("用户授权角色(全删全导)")
    public ApiResult grant(@RequestBody UserRoleEntity entity) {
        return service.grant(entity);
    }

    @GetMapping("/{userId}")
    @ApiOperation("查询用户已授权角色ID")
    public DataResult<Integer> getUserRoleId(@PathVariable Integer userId) {
        return service.getUserRoleId(userId);
    }
}

