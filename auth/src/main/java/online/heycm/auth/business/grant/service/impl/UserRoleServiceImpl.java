package online.heycm.auth.business.grant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import online.heycm.auth.business.grant.entity.UserRoleEntity;
import online.heycm.auth.business.grant.mapper.UserRoleMapper;
import online.heycm.auth.business.grant.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.heycm.utils.exception.ServiceException;
import online.heycm.utils.result.ApiResult;
import online.heycm.utils.result.DataResult;
import online.heycm.utils.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户授权角色服务实现
 * @author heycm
 * @since 2020-10-31
 */
@Service
@Transactional
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements UserRoleService {

    public ApiResult grant(UserRoleEntity entity) {
        if (entity.getUserId() == null) {
            throw new ServiceException("必须设置用户");
        }
        remove(new QueryWrapper<UserRoleEntity>().lambda().eq(UserRoleEntity::getUserId, entity.getUserId()));
        if (entity.getRoleId() != null) {
            boolean ok = save(entity);
            if (!ok) {
                throw new ServiceException("授权失败");
            }
        }
        return Result.ok("授权成功");
    }

    public DataResult<Integer> getUserRoleId(Integer userId) {
        UserRoleEntity one = getOne(new QueryWrapper<UserRoleEntity>().lambda().eq(UserRoleEntity::getUserId, userId));
        return Result.data(one == null ? null : one.getRoleId());
    }
}
