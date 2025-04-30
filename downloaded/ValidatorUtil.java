package com.lw.vcs.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @Author：lian.wei
 * @Date：2018/8/10 21:14
 * @Description：验证工具
 */
public class ValidatorUtil {
    private static Pattern pattern=Pattern.compile("1\\d{10}");

    /**
     * 手机号验证工具
     * @param src
     * @return
     */
    public static boolean isMobile(String src){
        if(StringUtils.isBlank(src)){
            return false;
        }

        return pattern.matcher(src).matches();
    }
}
