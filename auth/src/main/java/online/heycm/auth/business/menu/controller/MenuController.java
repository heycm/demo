package online.heycm.auth.business.menu.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.heycm.auth.base.BaseController;
import online.heycm.auth.business.menu.model.MenuData;
import online.heycm.auth.business.menu.model.MenuModel;
import online.heycm.auth.business.menu.service.impl.MenuServiceImpl;
import online.heycm.auth.business.model.TreeModel;
import online.heycm.auth.business.validation.group.Add;
import online.heycm.auth.business.validation.group.Edit;
import online.heycm.utils.result.ApiResult;
import online.heycm.utils.result.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单API
 * @author heycm
 * @since 2020-10-31
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单")
public class MenuController extends BaseController {

    @Autowired
    private MenuServiceImpl service;

    @PostMapping("/add")
    @ApiOperation("新增")
    public ApiResult add(@RequestBody @Validated(Add.class)MenuModel model) {
        return service.add(model);
    }

    @PostMapping("/edit")
    @ApiOperation("编辑")
    public ApiResult edit(@RequestBody @Validated(Edit.class)MenuModel model) {
        return service.edit(model);
    }

    @DeleteMapping("/{id:[1-9][0-9]*}")
    @ApiOperation("删除")
    public ApiResult delById(@PathVariable Integer id) {
        return service.delById(id);
    }

    @GetMapping("/{id:[1-9][0-9]*}")
    @ApiOperation("查询")
    public DataResult<MenuData> getById(@PathVariable Integer id) {
        return service.selectById(id);
    }

    @GetMapping("/{systemId:[1-9][0-9]*}/tree")
    @ApiOperation("获取菜单树(管理)")
    public DataResult<List<TreeModel<MenuData>>> getTreeBySystemId(@PathVariable Integer systemId) {
        return service.getTreeBySystemId(systemId);
    }

    @GetMapping("/{systemId:[1-9][0-9]*}/tree/actived")
    @ApiOperation("获取菜单树(激活)")
    public DataResult<List<TreeModel<MenuData>>> getActiveTree(@PathVariable Integer systemId) {
        return service.getActiveTree(systemId);
    }

    @GetMapping("/{systemId:[1-9][0-9]*}/tree/my")
    @ApiOperation("我的菜单")
    public DataResult<List<TreeModel<MenuData>>> getMyMenuTree(@PathVariable Integer systemId) {
        return service.getMyMenuTree(getRoleId(), systemId);
    }
}

