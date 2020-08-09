package online.heycm.minio.service.impl;

import online.heycm.minio.model.File;
import online.heycm.minio.mapper.FileMapper;
import online.heycm.minio.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author heycm
 * @since 2020-08-09
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

}
