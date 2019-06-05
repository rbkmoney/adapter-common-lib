package com.rbkmoney.adapter.common.utils.converter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {

    @Test
    public void addPrefixTest() {
        String str = "SomeString";
        String prefix = "TEST-";
        String result = "TEST-SomeString";
        assertEquals("Result string isn't equal expected one", result, StringUtils.addPrefix(str, prefix));
    }

    @Test
    public void removePrefixTest() {
        String str = "TEST-SomeString";
        String prefix = "TEST-";
        String result = "SomeString";
        assertEquals("Result string isn't equal expected one without prefix",
                result, StringUtils.removePrefix(str, prefix));
    }


}
