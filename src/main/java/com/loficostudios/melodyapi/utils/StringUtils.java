package com.loficostudios.melodyapi.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }
}
