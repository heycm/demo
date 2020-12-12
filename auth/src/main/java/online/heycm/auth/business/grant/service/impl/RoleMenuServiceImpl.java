package online.heycm.auth.business.grant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.heycm.auth.business.grant.entity.RoleMenuEntity;
import online.heycm.auth.business.grant.mapper.RoleMenuMapper;
import online.heycm.auth.business.grant.model.RoleMenuModel;
import online.heycm.auth.business.grant.service.RoleMenuService;
import online.heycm.utils.exception.ServiceException;
import online.heycm.utils.result.ApiResult;
import online.heycm.utils.result.DataResult;
import online.heycm.utils.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色授权菜单服务实现
 * @author heycm
 * @since 2020-10-31
 */
@Service
@Transactional
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenuEntity> implements RoleMenuService {

    public ApiResult grant(RoleMenuModel model) {
        if (model.getRoleId() == null || model.getSystemId() == null) {
            throw new ServiceException("必须设置角色和系统");
        }
        remove(new QueryWrapper<RoleMenuEntity>().lambda()
            .eq(RoleMenuEntity::getRoleId, model.getRoleId()).eq(RoleMenuEntity::getSystemId, model.getSystemId()));
        if (model.getMenuIds() == null || model.getMenuIds().isEmpty()) {
            return Result.ok("授权成功");
        }
        List<RoleMenuEntity> entities = model.getMenuIds().stream().map(x -> {
            RoleMenuEntity entity = new RoleMenuEntity();
            entity.setRoleId(model.getRoleId());
            entity.setSystemId(model.getSystemId());
            entity.setMenuId(x);
            return entity;
        }).collect(Collectors.toList());
        boolean ok = saveBatch(entities);
        if (!ok) {
            throw new ServiceException("授权失败");
        }
        return Result.ok("授权成功");
    }

    public DataResult<List<String>> getChecked(RoleMenuModel model) {
        List<RoleMenuEntity> data = list(new QueryWrapper<RoleMenuEntity>().lambda()
                .eq(RoleMenuEntity::getSystemId, model.getSystemId()).eq(RoleMenuEntity::getRoleId, model.getRoleId()));
        return Result.data(data.stream().map(x -> x.getMenuId().toString()).collect(Collectors.toList()));
    }
}
