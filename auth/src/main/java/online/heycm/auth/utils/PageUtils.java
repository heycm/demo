package online.heycm.auth.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import online.heycm.utils.param.PageParam;
import online.heycm.utils.result.PageResult;
import online.heycm.utils.result.Result;

/**
 * 分页构造工具
 *
 * @Author heycm@qq.com
 * @Date 2020-11-14 14:14
 */
public class PageUtils {

    private static final Integer DEFAULT_PAGE = 1;
    private static final Integer DEFAULT_SIZE = 15;

    /**
     * 初始化页码和每页数据量
     */
    public static <T> void init(PageParam<T> param) {
        if (param.getPage() == null || param.getPage() < 1) {
            param.setPage(DEFAULT_PAGE);
        }
        if (param.getSize() == null || param.getSize() < 1) {
            param.setSize(DEFAULT_SIZE);
        }
    }

    /**
     * 分页参数转MP分页器
     * @param param 分页参数
     */
    public static <T> Page<T> getMPPage(PageParam<?> param) {
        init(param);
        return new Page<>(param.getPage(), param.getSize());
    }

    /**
     * MP分页结果转PageResult
     * @param iPage 查询结果
     */
    public static <T> PageResult<T> toPageResult(IPage<T> iPage) {
        return Result.page(iPage.getPages(), iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getRecords());
    }

    /**
     * MP分页结果转PageResult
     * @param msg 接口消息
     * @param iPage 查询结果
     */
    public static <T> PageResult<T> toPageResult(String msg, IPage<T> iPage) {
        return Result.page(msg, iPage.getPages(), iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getRecords());
    }
}
