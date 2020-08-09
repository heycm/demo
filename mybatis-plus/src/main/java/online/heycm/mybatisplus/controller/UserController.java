package online.heycm.mybatisplus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import online.heycm.mybatisplus.model.User;
import online.heycm.mybatisplus.service.UserService;
import online.heycm.utils.result.ResModel;
import online.heycm.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author heycm
 * @since 2020-08-02
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/test")
    public ResModel test(@RequestBody User user) {
        boolean ok = user.insertOrUpdate();
        return Result.ok(user);
    }

    @PostMapping("/sel")
    public ResModel sel(@RequestBody User user) {
        // setEntity(user) 测试配置 select-strategy: not_empty
        User one = user.selectOne(new QueryWrapper<User>().setEntity(user));
        return Result.apiRes(one!=null, one, "查无此人");
    }

    @DeleteMapping("/{id}")
    public ResModel delById(@PathVariable Integer id) {
        boolean ok = userService.removeById(id);
        return Result.apiRes(ok, "删除成功", "删除失败");
    }
}

