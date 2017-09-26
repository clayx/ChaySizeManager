package com.chay.test.chaysizemanager.util;

/**
 * Created by Chay
 * Date on 2017/9/26.
 */

public class NumberParserUtils {

    public static int parseInt(String from) {
        if (Preconditions.isNullOrEmpty(from)) {
            return 0;
        }
        try {
            return Integer.parseInt(from);
        } catch (NumberFormatException numFormatExp) {
            return 0;
        }
    }

}
