package online.heycm.auth.business.user.service.impl;

import online.heycm.auth.business.user.entity.UserInfoEntity;
import online.heycm.auth.business.user.mapper.UserInfoMapper;
import online.heycm.auth.business.user.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户信息服务实现类
 * @author heycm
 * @since 2020-10-31
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements UserInfoService {

}
