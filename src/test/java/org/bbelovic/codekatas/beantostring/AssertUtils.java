package org.bbelovic.codekatas.beantostring;

import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

public final class AssertUtils {

    private AssertUtils() {
        // no instances allowed
    }

    public static void assertToString(final String actualToString, final String className, Object... expectedToStringContent) {
        assertTrue(actualToString.startsWith(className + "["));
        for (int i = 0; i < expectedToStringContent.length - 1; i += 2) {
            final String s = expectedToStringContent[i] + "=" + expectedToStringContent[i+1];
            assertTrue(actualToString.contains(s), format("Expected '%s' in toString message", s));
        }
        assertTrue(actualToString.endsWith("]"));
    }
}
