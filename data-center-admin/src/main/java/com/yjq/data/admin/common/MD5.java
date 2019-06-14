package com.yjq.data.admin.common;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-05
 */
public class MD5 {

    public MD5() {
    }

    public static String sign(String text, String key, String input_charset) {
        text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }

    public static String sign(String text) {
        return DigestUtils.md5Hex(getContentBytes(text, "utf-8"));
    }

    public static boolean verify(String text, String sign, String key, String input_charset) {
        text = text + key;
        String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
        return mysign.equals(sign);
    }

    private static byte[] getContentBytes(String content, String charset) {
        if (charset != null && !"".equals(charset)) {
            try {
                return content.getBytes(charset);
            } catch (UnsupportedEncodingException var3) {
                throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
            }
        } else {
            return content.getBytes();
        }
    }
}
