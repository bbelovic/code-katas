package org.bbelovic.codekatas;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class SimpleStringKataTest {

    private final SimpleStringKata simpleStringKata = new SimpleStringKata();

    @Test(dataProvider = "reverse")
    public void testReverse(String input, String expected) {
        final String actual = simpleStringKata.reverse(input);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "copyForward")
    public void copyForward(String input, String expected) {
        final String actual = simpleStringKata.copyForward(input);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "reverse")
    public Object [][] reverseDataProvider() {
        return new Object [][] {{"", ""},
                 {"b", "b"},
                 {"ab", "ba"},
                 {"abC", "Cba"},
                 {"Boris Belovic", "civoleB siroB"}
         };
    }

    @DataProvider(name = "copyForward")
    public Object [][] copyForwardDataProvider() {
        return new Object [][] { {"", ""},
                {"b", "b"}, {"Boris", "BBori"},
                {"Solomon", "SSolomo"}};
    }
}
