package online.heycm.auth.business.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.heycm.auth.business.grant.entity.RoleMenuEntity;
import online.heycm.auth.business.grant.service.impl.RoleMenuServiceImpl;
import online.heycm.auth.business.menu.entity.MenuEntity;
import online.heycm.auth.business.menu.mapper.MenuMapper;
import online.heycm.auth.business.menu.model.MenuData;
import online.heycm.auth.business.menu.model.MenuModel;
import online.heycm.auth.business.menu.service.MenuService;
import online.heycm.auth.business.model.TreeModel;
import online.heycm.auth.enums.Const;
import online.heycm.utils.exception.ServiceException;
import online.heycm.utils.result.ApiResult;
import online.heycm.utils.result.DataResult;
import online.heycm.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单服务实现
 * @author heycm
 * @since 2020-10-31
 */
@Service
@Transactional
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService {

    @Autowired
    RoleMenuServiceImpl roleMenuService;

    public ApiResult add(MenuModel model) {
        MenuEntity entity = model.toEntity();
        entity.setIndez(entity.getIndez() == null ? 1 : entity.getIndez());
        return Result.api(save(entity), Const.SAVE_OK, Const.SAVE_ERROR);
    }

    public ApiResult edit(MenuModel model) {
        boolean ok = updateById(model.toEntity());
        return Result.api(ok, Const.SAVE_OK, Const.SAVE_ERROR);
    }

    public ApiResult delById(Integer id) {
        List<MenuEntity> children = list(new QueryWrapper<MenuEntity>().lambda().eq(MenuEntity::getPid, id));
        if (!children.isEmpty()) {
            return Result.error("请先删除子菜单");
        }
        boolean ok = removeById(id);
        return Result.api(ok, Const.DEL_OK, Const.DEL_ERROR);
    }

    public DataResult<MenuData> selectById(Integer id) {
        MenuEntity entity = getById(id);
        if (entity == null) {
            throw new ServiceException("菜单不存在");
        }
        return Result.data(Const.SELECT_OK, new MenuData(entity));
    }

    /**
     * 获取系统菜单(管理时用)
     * @param systemId 系统ID
     * @return 菜单树
     */
    public DataResult<List<TreeModel<MenuData>>> getTreeBySystemId(Integer systemId) {
        List<MenuEntity> all = list(new QueryWrapper<MenuEntity>().lambda()
                .eq(MenuEntity::getSystemId, systemId).orderByAsc(MenuEntity::getIndez).orderByDesc(MenuEntity::getCreateAt));
        if (all.isEmpty()) {
            return Result.data(Const.SELECT_OK, Collections.emptyList());
        }
        List<TreeModel<MenuData>> tree = new ArrayList<>();
        List<MenuData> root = all.stream().filter(x -> x.getPid() == null).map(MenuData::new).collect(Collectors.toList());
        for (MenuData menu : root) {
            TreeModel<MenuData> item = new TreeModel<>();
            item.setKey(menu.getId().toString());
            item.setTitle(menu.getTitle());
            item.setOrigin(menu);
            item.setChildren(deepTree(menu.getId(), all));
            item.setIsLeaf(item.getChildren().isEmpty());
            tree.add(item);
        }
        return Result.data(Const.SELECT_OK, tree);
    }

    /**
     * 递归菜单树
     */
    public List<TreeModel<MenuData>> deepTree(Integer id, List<MenuEntity> all) {
        List<MenuData> curr = all.stream().filter(x -> id.equals(x.getPid())).map(MenuData::new).collect(Collectors.toList());
        if (curr.isEmpty()) {
            return Collections.emptyList();
        }
        List<TreeModel<MenuData>> tree = new ArrayList<>();
        for (MenuData menu : curr) {
            TreeModel<MenuData> item = new TreeModel<>();
            item.setKey(menu.getId().toString());
            item.setTitle(menu.getTitle());
            item.setOrigin(menu);
            item.setChildren(deepTree(menu.getId(), all));
            item.setIsLeaf(item.getChildren().isEmpty());
            tree.add(item);
        }
        return tree;
    }

    /**
     * 获取已启用的菜单(授权时用)
     * @param systemId 系统ID
     * @return 菜单树
     */
    public DataResult<List<TreeModel<MenuData>>> getActiveTree(Integer systemId) {
        List<MenuEntity> all = list(new QueryWrapper<MenuEntity>().lambda()
                .eq(MenuEntity::getSystemId, systemId).eq(MenuEntity::getEnable, 0));
        if (all.isEmpty()) {
            return Result.data(Const.SELECT_OK, Collections.emptyList());
        }
        List<TreeModel<MenuData>> tree = new ArrayList<>();
        List<MenuData> root = all.stream().filter(x -> x.getPid() == null).map(MenuData::new).collect(Collectors.toList());
        for (MenuData menu : root) {
            TreeModel<MenuData> item = new TreeModel<>();
            item.setKey(menu.getId().toString());
            item.setTitle(menu.getTitle());
            item.setOrigin(menu);
            item.setChildren(deepTree(menu.getId(), all));
            item.setIsLeaf(item.getChildren().isEmpty());
            tree.add(item);
        }
        return Result.data(Const.SELECT_OK, tree);
    }

    /**
     * 获取我的菜单
     * @param roleId Token中的角色ID
     * @param systemId 系统ID
     * @return 菜单树
     */
    public DataResult<List<TreeModel<MenuData>>> getMyMenuTree(Integer roleId, Integer systemId) {
        if (roleId == null) {
            throw new ServiceException("您未获得角色授权");
        }
        List<RoleMenuEntity> relations = roleMenuService.list(new QueryWrapper<RoleMenuEntity>().lambda()
                .eq(RoleMenuEntity::getRoleId, roleId).eq(RoleMenuEntity::getSystemId, systemId));
        if (relations.isEmpty()) {
            return Result.data(Const.SELECT_OK, Collections.emptyList());
        }
        List<Integer> menuIds = relations.stream().map(RoleMenuEntity::getMenuId).collect(Collectors.toList());
        List<MenuEntity> all = list(new QueryWrapper<MenuEntity>().lambda()
                .in(MenuEntity::getId, menuIds)
                .eq(MenuEntity::getEnable, 0)
                .orderByAsc(MenuEntity::getIndez));
        if (all.isEmpty()) {
            return Result.data(Const.SELECT_OK, Collections.emptyList());
        }
        List<TreeModel<MenuData>> tree = new ArrayList<>();
        List<MenuData> root = all.stream().filter(x -> x.getPid() == null).map(MenuData::new).collect(Collectors.toList());
        for (MenuData menu : root) {
            TreeModel<MenuData> item = new TreeModel<>();
            item.setKey(menu.getId().toString());
            item.setTitle(menu.getTitle());
            item.setOrigin(menu);
            item.setChildren(deepTree(menu.getId(), all));
            item.setIsLeaf(item.getChildren().isEmpty());
            tree.add(item);
        }
        return Result.data(Const.SELECT_OK, tree);
    }
}
