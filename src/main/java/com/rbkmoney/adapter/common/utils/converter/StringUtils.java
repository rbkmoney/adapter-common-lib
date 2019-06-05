package com.rbkmoney.adapter.common.utils.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils {

    public static final String EMPTY_STRING = "";

    public static String addPrefix(String string, String prefix) {
        return prefix + string;
    }

    public static String removePrefix(String string, String prefix) {
        if (string != null && string.startsWith(prefix)) {
            return string.replace(prefix, "");
        } else {
            return string;
        }
    }

}
