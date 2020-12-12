package online.heycm.auth.business.user.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.heycm.auth.base.BaseController;
import online.heycm.auth.business.user.model.ChangePwd;
import online.heycm.auth.business.user.model.UserData;
import online.heycm.auth.business.user.model.UserModel;
import online.heycm.auth.business.user.service.impl.UserServiceImpl;
import online.heycm.auth.business.validation.group.Add;
import online.heycm.auth.business.validation.group.Edit;
import online.heycm.utils.param.PageParam;
import online.heycm.utils.result.ApiResult;
import online.heycm.utils.result.DataResult;
import online.heycm.utils.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户API
 * @author heycm
 * @since 2020-10-31
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class UserController extends BaseController {

    @Autowired
    UserServiceImpl service;

    @PostMapping("/add")
    @ApiOperation("新增")
    public ApiResult save(@RequestBody @Validated(Add.class) UserModel model) {
        return service.insert(model);
    }

    @PostMapping("/edit")
    @ApiOperation("更新")
    public ApiResult modify(@RequestBody @Validated(Edit.class) UserModel model) {
        return service.modify(model);
    }

    @DeleteMapping("/{id:[1-9][0-9]*}")
    @ApiOperation("删除")
    public ApiResult delById(@PathVariable Integer id) {
        return service.delById(id);
    }

    @GetMapping("/{id:[1-9][0-9]*}")
    @ApiOperation("查询")
    public DataResult<UserData> getById(@PathVariable Integer id) {
        return service.selectById(id);
    }

    @PostMapping("/page")
    @ApiOperation("分页查询")
    public PageResult<UserData> page(@RequestBody PageParam<String> param) {
        return service.getPage(param);
    }

    @GetMapping("/{username}/check")
    @ApiOperation("检查用户名是否可用")
    public ApiResult checkUsername(@PathVariable String username) {
        return service.checkUsername(username);
    };

    @GetMapping("/me")
    @ApiOperation("我的用户信息")
    public DataResult<UserData> myInfo() {
        return service.myInfo(getUserId());
    }

    @PostMapping("/change")
    @ApiOperation("修改密码")
    public ApiResult changePwd(@RequestBody ChangePwd model) {
        return service.changePwd(getUserId(), model);
    }
}

