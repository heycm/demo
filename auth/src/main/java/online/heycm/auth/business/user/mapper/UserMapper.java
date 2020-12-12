package online.heycm.auth.business.user.mapper;

import online.heycm.auth.business.user.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户Mapper接口
 * @author heycm
 * @since 2020-10-31
 */
public interface UserMapper extends BaseMapper<UserEntity> {
    List<UserEntity> getList();
}
