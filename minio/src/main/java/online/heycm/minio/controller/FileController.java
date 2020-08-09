package online.heycm.minio.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import online.heycm.minio.model.File;
import online.heycm.minio.service.FileService;
import online.heycm.minio.utils.MinioUtil;
import online.heycm.utils.result.ResModel;
import online.heycm.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author heycm
 * @since 2020-08-09
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    MinioUtil minioUtil;
    @Autowired
    FileService fileService;

    /**
     * 上传文件
     * @param file 文件
     * @return ResModel
     * @throws IOException IO异常
     */
    @PostMapping
    public ResModel upload( MultipartFile file) throws IOException {
        if (file == null) {
            return Result.error("上传文件为空");
        }
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 上传
        String url = minioUtil.upload(file.getInputStream(), uuid + suffix, file.getContentType());
        // 上传失败
        if (url == null) {
            return Result.error("上传失败");
        }
        // 写入数据库
        File fileModel = new File();
        fileModel.setFileName(fileName);
        fileModel.setFileSuffix(suffix);
        fileModel.setFileSize((int)file.getSize() / 1024);
        fileModel.setFileUrl(url);
        fileModel.setFileUuid(uuid);
        boolean ok = fileModel.insert();
        // 写入数据库失败
        if (!ok) {
            // 删除服务器文件
            minioUtil.remove(uuid + suffix);
        }
        return Result.ok(fileModel);
    }

    /**
     * 删除文件
     * @param uuid 文件UUID
     * @return
     */
    @DeleteMapping("/{uuid}")
    public ResModel remove(@PathVariable String uuid) {
        File file = fileService.getOne(new QueryWrapper<File>().lambda().eq(File::getFileUuid, uuid));
        if (file == null) {
            return Result.ok("成功");
        }
        // 删除服务器中的文件
        boolean ok = minioUtil.remove(uuid + file.getFileSuffix());
        if (!ok) {
            return Result.error("失败");
        }
        // 删除数据库记录
        ok = file.deleteById();
        return Result.apiRes(ok, "成功", "失败");
    }

}

