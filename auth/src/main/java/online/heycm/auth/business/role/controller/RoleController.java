package online.heycm.auth.business.role.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.heycm.auth.business.role.model.RoleData;
import online.heycm.auth.business.role.model.RoleModel;
import online.heycm.auth.business.role.service.impl.RoleServiceImpl;
import online.heycm.auth.business.validation.group.Add;
import online.heycm.auth.business.validation.group.Edit;
import online.heycm.utils.param.PageParam;
import online.heycm.utils.result.ApiResult;
import online.heycm.utils.result.DataResult;
import online.heycm.utils.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色API
 * @author heycm
 * @since 2020-10-31
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色")
public class RoleController {

    @Autowired
    private RoleServiceImpl service;

    @PostMapping("/add")
    @ApiOperation("新增")
    public ApiResult addOne(@RequestBody @Validated(Add.class) RoleModel model) {
        return service.addOne(model);
    }

    @PostMapping("/edit")
    @ApiOperation("更新")
    public ApiResult edit(@RequestBody @Validated(Edit.class) RoleModel model) {
        return service.edit(model);
    }

    @GetMapping("/{id:[1-9][0-9]*}")
    @ApiOperation("查询")
    public DataResult<RoleData> getById(@PathVariable Integer id) {
        return service.selectById(id);
    }

    @DeleteMapping("/{id:[1-9][0-9]*}")
    @ApiOperation("删除")
    public ApiResult delById(@PathVariable Integer id) {
        return service.delById(id);
    }

    @PostMapping("/page")
    @ApiOperation("分页查询")
    public PageResult<RoleData> page(@RequestBody PageParam<String> param) {
        return service.getPage(param);
    }

    @GetMapping
    @ApiOperation("关键字查询")
    public DataResult<List<RoleData>> list(String keyword) {
        return service.getList(keyword);
    }
}

