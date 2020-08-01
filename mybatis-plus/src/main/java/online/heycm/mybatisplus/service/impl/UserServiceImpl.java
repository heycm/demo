package online.heycm.mybatisplus.service.impl;

import online.heycm.mybatisplus.model.User;
import online.heycm.mybatisplus.mapper.UserMapper;
import online.heycm.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author heycm
 * @since 2020-08-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
