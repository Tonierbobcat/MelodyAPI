package com.loficostudios.melodyapi.utils;

import com.google.common.base.Strings;

@Deprecated
public class StringUtils {
    public static boolean isNullOrEmpty(String string) {
        return Strings.isNullOrEmpty(string);
    }
}
