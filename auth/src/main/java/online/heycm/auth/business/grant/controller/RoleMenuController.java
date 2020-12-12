package online.heycm.auth.business.grant.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.heycm.auth.base.BaseController;
import online.heycm.auth.business.grant.model.RoleMenuModel;
import online.heycm.auth.business.grant.service.impl.RoleMenuServiceImpl;
import online.heycm.utils.result.ApiResult;
import online.heycm.utils.result.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色菜单
 * @author heycm
 * @since 2020-10-31
 */
@RestController
@RequestMapping("/role/menu")
@Api(tags = "授权 - 角色菜单")
public class RoleMenuController extends BaseController {

    @Autowired
    private RoleMenuServiceImpl service;

    @PostMapping
    @ApiOperation("角色授权菜单(全删全导)")
    public ApiResult grant(@RequestBody RoleMenuModel model) {
        return service.grant(model);
    }

    @PostMapping("/checked")
    @ApiOperation("查询已授权菜单ID集合")
    public DataResult<List<String>> getChecked(@RequestBody RoleMenuModel model) {
        return service.getChecked(model);
    }
}

