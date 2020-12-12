package online.heycm.auth.business.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.heycm.auth.business.system.entity.SystemEntity;
import online.heycm.auth.business.system.mapper.SystemMapper;
import online.heycm.auth.business.system.model.SystemData;
import online.heycm.auth.business.system.model.SystemModel;
import online.heycm.auth.business.system.service.SystemService;
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
 * 应用服务实现
 * @author heycm
 * @since 2020-10-31
 */
@Service
@Transactional
public class SystemServiceImpl extends ServiceImpl<SystemMapper, SystemEntity> implements SystemService {

    public ApiResult add(SystemModel model) {
        SystemEntity getByCode = getOne(new QueryWrapper<SystemEntity>().lambda().eq(SystemEntity::getCode, model.getCode()));
        if (getByCode != null) {
            return Result.error("应用编码重复");
        }
        SystemEntity entity = model.toEntity();
        entity.setIndez(entity.getIndez() == null ? 1 : entity.getIndez());
        return Result.api(save(entity), Const.SAVE_OK, Const.SAVE_ERROR);
    }

    public ApiResult edit(SystemModel model) {
        boolean ok = updateById(model.toEntity());
        return Result.api(ok, Const.SAVE_OK, Const.SAVE_ERROR);
    }

    public DataResult<SystemData> selectById(Integer id) {
        SystemEntity entity = getById(id);
        if (entity == null) {
            throw new ServiceException("应用不存在");
        }
        return Result.data(Const.SELECT_OK, new SystemData(entity));
    }

    public ApiResult delById(Integer id) {
        boolean ok = removeById(id);
        return Result.api(ok, Const.DEL_OK, Const.DEL_ERROR);
    }

    public PageResult<SystemData> getPage(PageParam<String> param) {
        Page<SystemEntity> page = PageUtils.getMPPage(param);
        String keyword = param.getParam();
        LambdaQueryWrapper<SystemEntity> qw = new QueryWrapper<SystemEntity>().lambda();
        qw.orderByAsc(SystemEntity::getIndez).orderByDesc(SystemEntity::getCreateAt);
        qw.and(StrUtil.isNotBlank(keyword), i ->
                i.like(SystemEntity::getName, keyword).or().like(SystemEntity::getCode, keyword));
        Page<SystemEntity> iPage = page(page, qw);
        List<SystemData> data = iPage.getRecords().stream().map(SystemData::new).collect(Collectors.toList());
        return Result.page(Const.SELECT_OK, iPage.getPages(), iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), data);
    }

    public DataResult<List<SystemData>> getList(String keyword) {
        LambdaQueryWrapper<SystemEntity> qw = new QueryWrapper<SystemEntity>().lambda();
        qw.orderByAsc(SystemEntity::getIndez).orderByDesc(SystemEntity::getCreateAt);
        qw.and(StrUtil.isNotBlank(keyword), i ->
                i.like(SystemEntity::getName, keyword).or().like(SystemEntity::getCode, keyword));
        List<SystemEntity> list = list(qw);
        return Result.data(Const.SELECT_OK, list.stream().map(SystemData::new).collect(Collectors.toList()));
    }
}
