package org.bbelovic.codekatas.beantostring;

import org.testng.annotations.Test;

import static org.bbelovic.codekatas.beantostring.AssertUtils.assertToString;

@Test
public class BeanToStringBuilderTest {

    @Test(dataProvider = "beanAndToStringContentsDataProvider", dataProviderClass = BeanToStringDataProvider.class)
    public void test(final Object bean, final Object[] toStringContents) {
        assertToString(bean.toString(), bean.getClass().getSimpleName(), toStringContents);
    }
}
