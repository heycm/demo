package online.heycm.minio.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author heycm
 * @since 2020-08-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("file")
public class File extends Model<File> {

    private static final long serialVersionUID=1L;

    /**
     * 文件ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文件UUID
     */
    private String fileUuid;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件后缀
     */
    private String fileSuffix;

    /**
     * 文件大小（KB）
     */
    private Integer fileSize;

    /**
     * 文件类型：0 图片 1 音频 2 其他
     */
    private Integer fileType;

    /**
     * 文件外链
     */
    private String fileUrl;

    /**
     * 逻辑删除：0 未删除 1 已删除
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
