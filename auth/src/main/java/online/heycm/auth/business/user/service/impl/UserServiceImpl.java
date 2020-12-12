package online.heycm.auth.business.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.heycm.auth.business.user.entity.UserEntity;
import online.heycm.auth.business.user.mapper.UserMapper;
import online.heycm.auth.business.user.model.ChangePwd;
import online.heycm.auth.business.user.model.UserData;
import online.heycm.auth.business.user.model.UserModel;
import online.heycm.auth.business.user.service.UserService;
import online.heycm.auth.enums.Const;
import online.heycm.auth.utils.PageUtils;
import online.heycm.auth.utils.PasswordUtils;
import online.heycm.utils.exception.ServiceException;
import online.heycm.utils.param.PageParam;
import online.heycm.utils.result.ApiResult;
import online.heycm.utils.result.DataResult;
import online.heycm.utils.result.PageResult;
import online.heycm.utils.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务实现类
 * @author heycm
 * @since 2020-10-31
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {


    /**
     * 新增
     * @param model
     */
    public ApiResult insert(UserModel model) {
        UserEntity one = getByUsername(model.getUsername());
        if (one != null) {
            return Result.error("用户已存在");
        }
        UserEntity entity = model.toEntity();
        entity.setEnable(0);
        String salt = PasswordUtils.getSalt();
        String encrypt = PasswordUtils.encrypt("123456", salt);
        entity.setSalt(salt);
        entity.setPassword(encrypt);
        return Result.api(save(entity), Const.SAVE_OK, Const.SAVE_ERROR);
    }

    /**
     * 修改
     * @param model
     */
    public ApiResult modify(UserModel model) {
        UserEntity entity = new UserEntity();
        entity.setId(model.getId());
        entity.setNickname(model.getNickname());
        entity.setEnable(model.getEnable());
        updateById(entity);
        return Result.api(updateById(entity), Const.SAVE_OK, Const.SAVE_ERROR);
    }

    /**
     * 根据用户名查找用户
     * @param username
     */
    public UserEntity getByUsername(String username) {
        return StrUtil.isBlank(username) ? null : getOne(new QueryWrapper<UserEntity>().lambda().eq(UserEntity::getUsername, username));
    }

    /**
     * 根据ID删除
     * @param id 用户ID
     */
    public ApiResult delById(Integer id) {
        return Result.api(removeById(id), Const.DEL_OK, Const.DEL_ERROR);
    }

    /**
     * 根据ID查询
     * @param id 用户ID
     */
    public DataResult<UserData> selectById(Integer id) {
        UserEntity entity = getById(id);
        if (entity == null) {
            throw new ServiceException("用户不存在");
        }
        return Result.data(Const.SELECT_OK, new UserData(entity));
    }

    /**
     * 分页查询
     * @param param 查询参数
     */
    public PageResult<UserData> getPage(PageParam<String> param) {
        Page<UserEntity> page = PageUtils.getMPPage(param);
        LambdaQueryWrapper<UserEntity> wrapper = new QueryWrapper<UserEntity>().lambda().orderByDesc(UserEntity::getCreateAt);
        String keyword = param.getParam();
        wrapper.and(StrUtil.isNotBlank(keyword), i ->
                i.like(UserEntity::getUsername, keyword).or().like(UserEntity::getNickname, keyword));
        IPage<UserEntity> iPage = page(page, wrapper);
        List<UserEntity> records = iPage.getRecords();
        List<UserData> data = records.stream().map(UserData::new).collect(Collectors.toList());
        return Result.page(Const.SELECT_OK, iPage.getPages(), iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), data);
    }

    public ApiResult checkUsername(String username) {
        UserEntity user = getByUsername(username);
        return Result.api(user == null, null);
    }

    /**
     * 我的用户信息
     * @param userId
     * @return
     */
    public DataResult<UserData> myInfo(Integer userId) {
        return Result.data(Const.SELECT_OK, new UserData(getById(userId)));
    }

    /**
     * 修改密码
     * @param userId
     * @param model
     * @return
     */
    public ApiResult changePwd(Integer userId, ChangePwd model) {
        UserEntity user = getById(userId);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        String encrypt = PasswordUtils.encrypt(model.getOldPwd(), user.getSalt());
        if (!user.getPassword().equals(encrypt)) {
            return Result.error("原密码错误");
        }
        user.setSalt(PasswordUtils.getSalt());
        user.setPassword(PasswordUtils.encrypt(model.getNewPwd(), user.getSalt()));
        boolean ok = updateById(user);
        return Result.api(ok, Const.SAVE_OK, Const.SAVE_ERROR);
    }
}
