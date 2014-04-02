package org.bbelovic.codekatas.beantostring;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;

@Test
public class ToStringStrategyTest {

    private static final PrimitiveTypesBean PRIMITIVE_TYPES_BEAN = new PrimitiveTypesBean(true, (byte)0, (short)400, 'X', 20, 90L, 150.0f, 100.0d);
    private static final CollectionBean COLLECTION_BEAN = testCollectionBean();
    private static final ConfidentialData CONFIDENTIAL_DATA_BEAN = new ConfidentialData("jdoe", "abcd", "123", "jdoe@company.com");
    private static final CompositeBean COMPOSITE_BEAN = testCompositeBean();
    private static final EmptyBean EMPTY_BEAN = new EmptyBean();

    private static final String EXPECTED_TO_STRING_PRIMITIVES = "PrimitiveTypesBean[boolField=true, " +
            "byteField=0, charField=X, doubleField=100.0, floatField=150.0, intField=20, longField=90, shortField=400]";

    private static final String EXPECTED_TO_STRING_COLLECTIONS = "CollectionBean[stringList=[a, b, c], " +
            "stringMap={key2=value2, key1=value1}, stringSet=[element2, element3, element1]]";

    private static final String EXPECTED_TO_STRING_COMPOSITE = "CompositeBean[" +
            "collectionBean=CollectionBean[stringList=null, stringMap={key=value}, stringSet=[]], stringField=abc]";

    private static final String EXPECTED_TO_STRING_IGNORED_FIELDS = "ConfidentialData[email=jdoe@company.com, username=jdoe]";

    private static final String EXPECTED_TO_STRING_EMPTY_BEAN = "EmptyBean[]";

    @Test(dataProvider = "toStringDataProvider")
    public void should_construct_correct_toString_representation_containing_all_internal_bean_fields(ToStringStrategy toStringStrategy, String expectedToString) {
        assertEquals(toStringStrategy.toString(), expectedToString);
    }

    @DataProvider(name = "toStringDataProvider")
    private Object [][] toStringInputBeans() {
        return new Object[][] {{new ReadMethodToStringStrategy(PRIMITIVE_TYPES_BEAN), EXPECTED_TO_STRING_PRIMITIVES},
                {new ReadMethodToStringStrategy(COLLECTION_BEAN), EXPECTED_TO_STRING_COLLECTIONS},
                {new ReadMethodToStringStrategy(CONFIDENTIAL_DATA_BEAN), EXPECTED_TO_STRING_IGNORED_FIELDS},
                {new ReadMethodToStringStrategy(COMPOSITE_BEAN), EXPECTED_TO_STRING_COMPOSITE},
                {new ReadMethodToStringStrategy(EMPTY_BEAN), EXPECTED_TO_STRING_EMPTY_BEAN},
                {new DirectFieldAccessToStringStrategy(PRIMITIVE_TYPES_BEAN), EXPECTED_TO_STRING_PRIMITIVES},
                {new DirectFieldAccessToStringStrategy(COLLECTION_BEAN), EXPECTED_TO_STRING_COLLECTIONS},
                {new DirectFieldAccessToStringStrategy(CONFIDENTIAL_DATA_BEAN), EXPECTED_TO_STRING_IGNORED_FIELDS},
                {new DirectFieldAccessToStringStrategy(COMPOSITE_BEAN), EXPECTED_TO_STRING_COMPOSITE},
                {new DirectFieldAccessToStringStrategy(EMPTY_BEAN), EXPECTED_TO_STRING_EMPTY_BEAN}};
    }

    private static CollectionBean testCollectionBean() {
        final List<String> stringList = Arrays.asList("a", "b", "c");
        final Map<String, String> stringMap = new HashMap<>();
        stringMap.put("key1", "value1");
        stringMap.put("key2", "value2");
        final Set<String> stringSet = new HashSet<>(Arrays.asList("element1", "element2", "element3"));
        return new CollectionBean(stringList, stringMap, stringSet);
    }

    private static CompositeBean testCompositeBean() {
        final Map<String, String> testMap = new HashMap<>();
        testMap.put("key", "value");
        final CollectionBean collectionBean = new CollectionBean(null, testMap, new HashSet<String>());
        return new CompositeBean(collectionBean, "abc");
    }
}
