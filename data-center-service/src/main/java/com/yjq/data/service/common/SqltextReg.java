package com.yjq.data.service.common;

import java.util.regex.Pattern;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-18
 */
public class SqltextReg {

    public final static String DOLLAR_SYMBOL_PATTERN_STR = "^#{[A-Za-z0-9]+}$";

    public final static String HASH_SYMBOL_PATTERN_STR = "^${[A-Za-z0-9]+}$";

    public final static Pattern DOLLAR_SYMBOL_PATTERN = Pattern.compile(DOLLAR_SYMBOL_PATTERN_STR);

    public final static Pattern HASH_SYMBOL_PATTERN = Pattern.compile(HASH_SYMBOL_PATTERN_STR);

    public static void matchDollarSymbol(String sqltext) {

    }

}
