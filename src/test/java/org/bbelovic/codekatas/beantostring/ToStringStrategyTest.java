package org.bbelovic.codekatas.beantostring;

import org.testng.annotations.Test;

import static org.bbelovic.codekatas.beantostring.AssertUtils.assertToString;

@Test
public class ToStringStrategyTest {

    @Test(dataProvider = "toStringStrategyDataProvider", dataProviderClass = BeanToStringDataProvider.class)
    public void should_construct_toString_representation_containing_all_required_bean_fields(ToStringStrategy tss, String beanName, Object[] beanData) {
        assertToString(tss.toString(), beanName, beanData);
    }


}
