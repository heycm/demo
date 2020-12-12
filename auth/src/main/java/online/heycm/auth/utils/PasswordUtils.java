package online.heycm.auth.utils;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.MD5;

/**
 * 加解密
 * @Author heycm@qq.com
 * @Date 2020-11-28 15:15
 */
public class PasswordUtils {

    /**
     * 生成随机盐值
     */
    public static String getSalt() {
        return RandomUtil.randomString(50);
    }

    /**
     * 加密
     * @param password 明文密码
     * @param salt 加密盐值
     * @return 密文
     */
    public static String encrypt(String password, String salt) {
        MD5 md5 = new MD5(salt.getBytes(), 1024);
        return md5.digestHex(password);
    }

}
