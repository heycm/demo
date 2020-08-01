package online.heycm.utils.operation;

import java.math.BigDecimal;

/**
 * BigDecimal 算术运算
 *
 * @Author heycm@qq.com
 * @Date 2020-08-01 21:17
 */
public class DecimalUtil {

    // 商保留小数位数
    private static final int DIV_SCALE = 2;
    // 四舍五入
    private static final int DIV_ROUND_HALF_UP = BigDecimal.ROUND_HALF_UP;

    /**
     * 加法： 所有参数相加
     * @return 和
     */
    public static BigDecimal add(BigDecimal... decimals) {
        if (decimals.length <= 1){
            throw new RuntimeException("BigDecimal lacks parameters for addition");
        }
        BigDecimal addRes = new BigDecimal("0");
        for (BigDecimal decimal : decimals) {
            addRes = addRes.add(decimal);
        }
        return addRes;
    }

    /**
     * 减法： 第一个参数为被减数，其余为减数
     * @return 差
     */
    public static BigDecimal subtract(BigDecimal... decimals) {
        if (decimals.length <= 1){
            throw new RuntimeException("BigDecimal lacks parameters for subtraction");
        }
        BigDecimal subRes = decimals[0];
        for (int i = 1; i < decimals.length; i++){
            subRes = subRes.subtract(decimals[i]);
        }
        return subRes;
    }

    /**
     * 乘法： 所有参数相乘
     * @return 积
     */
    public static BigDecimal multiply(BigDecimal... decimals) {
        if (decimals.length <= 1){
            throw new RuntimeException("BigDecimal lacks parameters for multiplication");
        }
        BigDecimal mulRes = decimals[0];
        for (int i = 1; i < decimals.length; i++){
            mulRes = mulRes.multiply(decimals[i]);
        }
        return mulRes;
    }

    /**
     * 除法： 第一个参数为被除数，其余为除数
     * @return 商
     */
    public static BigDecimal divide(BigDecimal... decimals) {
        if (decimals.length <= 1){
            throw new RuntimeException("BigDecimal lacks parameters for division");
        }
        BigDecimal mulRes = decimals[0];
        for (int i = 1; i < decimals.length; i++){
            mulRes = mulRes.divide(decimals[i], DIV_SCALE, DIV_ROUND_HALF_UP);
        }
        return mulRes;
    }
}
