package online.heycm.auth.business.role.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.heycm.auth.business.role.entity.RoleEntity;
import online.heycm.auth.business.role.mapper.RoleMapper;
import online.heycm.auth.business.role.model.RoleData;
import online.heycm.auth.business.role.model.RoleModel;
import online.heycm.auth.business.role.service.RoleService;
import online.heycm.auth.enums.Const;
import online.heycm.auth.utils.PageUtils;
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
 * 角色服务实现类
 * @author heycm
 * @since 2020-10-31
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    /**
     * 新增角色
     * @param model 新增模型
     * @return
     */
    public ApiResult addOne(RoleModel model) {
        RoleEntity entity = model.toEntity();
        RoleEntity byName = getByName(entity.getName());
        if (byName != null) {
            return Result.error("角色已存在");
        }
        entity.setType(null);
        return Result.api(save(entity), Const.SAVE_OK, Const.SAVE_ERROR);
    }

    /**
     * 根据角色英文名称查询
     * @param name 角色英文名称
     * @return
     */
    private RoleEntity getByName(String name) {
        return StrUtil.isBlank(name) ? null : getOne(new QueryWrapper<RoleEntity>().lambda().eq(RoleEntity::getName, name));
    }

    /**
     * 编辑角色
     * @param model 编辑模型
     * @return
     */
    public ApiResult edit(RoleModel model) {
        RoleEntity entity = model.toEntity();
        entity.setName(null);
        entity.setType(null);
        return Result.api(updateById(entity), Const.SAVE_OK, Const.SAVE_ERROR);
    }

    /**
     * 根据ID查询
     * @param id 角色ID
     * @return
     */
    public DataResult<RoleData> selectById(Integer id) {
        RoleEntity byId = getById(id);
        if (byId == null) {
            throw new ServiceException("角色不存在");
        }
        return Result.data(Const.SELECT_OK, new RoleData(byId));
    }

    /**
     * 根据ID删除
     * @param id 角色ID
     * @return
     */
    public ApiResult delById(Integer id) {
        return Result.api(removeById(id), Const.DEL_OK, Const.DEL_ERROR);
    }

    public PageResult<RoleData> getPage(PageParam<String> param) {
        Page<RoleEntity> page = PageUtils.getMPPage(param);
        String keyword = param.getParam();
        LambdaQueryWrapper<RoleEntity> wrapper = new QueryWrapper<RoleEntity>().lambda().orderByDesc(RoleEntity::getCreateAt);
        wrapper.and(StrUtil.isNotBlank(keyword), i ->
                i.like(RoleEntity::getName, keyword).or().like(RoleEntity::getNameZh, keyword));
        Page<RoleEntity> iPage = page(page, wrapper);
        List<RoleEntity> records = iPage.getRecords();
        List<RoleData> data = records.stream().map(RoleData::new).collect(Collectors.toList());
        return Result.page(Const.SELECT_OK, iPage.getPages(), iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), data);
    }

    /**
     * 关键字查询
     * @param keyword
     * @return
     */
    public DataResult<List<RoleData>> getList(String keyword) {
        LambdaQueryWrapper<RoleEntity> wrapper = new QueryWrapper<RoleEntity>().lambda().orderByDesc(RoleEntity::getCreateAt);
        wrapper.and(StrUtil.isNotBlank(keyword), i ->
                i.like(RoleEntity::getName, keyword).or().like(RoleEntity::getNameZh, keyword));
        List<RoleEntity> list = list(wrapper);
        return Result.data(Const.SELECT_OK, list.stream().map(RoleData::new).collect(Collectors.toList()));
    }
}
