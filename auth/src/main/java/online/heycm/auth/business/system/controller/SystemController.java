package online.heycm.auth.business.system.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.heycm.auth.business.system.model.SystemData;
import online.heycm.auth.business.system.model.SystemModel;
import online.heycm.auth.business.system.service.impl.SystemServiceImpl;
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
 * 应用API
 * @author heycm
 * @since 2020-10-31
 */
@RestController
@RequestMapping("/system")
@Api(tags = "应用")
public class SystemController {

    @Autowired
    private SystemServiceImpl service;

    @PostMapping("/add")
    @ApiOperation("新增")
    public ApiResult add(@RequestBody @Validated(Add.class) SystemModel model) {
        return service.add(model);
    }

    @PostMapping("/edit")
    @ApiOperation("编辑")
    public ApiResult edit(@RequestBody @Validated(Edit.class) SystemModel model) {
        return service.edit(model);
    }

    @GetMapping("/{id:[1-9][0-9]*}")
    @ApiOperation("查询")
    public DataResult<SystemData> getById(@PathVariable Integer id) {
        return service.selectById(id);
    }

    @DeleteMapping("/{id:[1-9][0-9]*}")
    @ApiOperation("删除")
    public ApiResult delById(@PathVariable Integer id) {
        return service.delById(id);
    }

    @PostMapping("/page")
    @ApiOperation("分页")
    public PageResult<SystemData> page(@RequestBody PageParam<String> param) {
        return service.getPage(param);
    }

    @GetMapping
    @ApiOperation("关键字查询")
    public DataResult<List<SystemData>> list(String keyword) {
        return service.getList(keyword);
    }
}

