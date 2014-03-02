package org.bbelovic.codekatas.beantostring;

import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;

@Test
public class BeanToStringBuilderTest {

    private static final String EXPECTED_TO_STRING_PRIMITIVES = "PrimitiveTypesBean[boolField=true, " +
            "byteField=0, charField=X, doubleField=100.0, floatField=150.0, intField=20, longField=90, shortField=400]";

    private static final String EXPECTED_TO_STRING_COLLECTIONS = "CollectionBean[stringList=[a, b, c], " +
            "stringMap={key2=value2, key1=value1}, stringSet=[element2, element3, element1]]";

    private static final String EXPECTED_TO_STRING_COMPOSITE = "CompositeBean[" +
            "collectionBean=CollectionBean[stringList=null, stringMap={key=value}, stringSet=[]], stringField=abc]";

    private static final String EXPECTED_TO_STRING_IGNORED_FIELDS = "ConfidentialData[email=jdoe@company.com, username=jdoe]";

    private static final String EXPECTED_TO_STRING_EMPTY_BEAN = "EmptyBean[]";

    @Test
    public void unified_toString_for_empty_bean_should_contain_only_simple_class_name() {
        assertEquals(new EmptyBean().toString(), EXPECTED_TO_STRING_EMPTY_BEAN);
    }

    @Test
    public void should_ignore_fields_marked_with_loggingignored_annotation_in_toString() {
        final ConfidentialData confidentialData = new ConfidentialData("jdoe", "abcd", "123", "jdoe@company.com");
        assertEquals(confidentialData.toString(), EXPECTED_TO_STRING_IGNORED_FIELDS);
    }

    @Test
    public void should_construct_unified_toString_for_bean_with_only_primitive_types() {
        final PrimitiveTypesBean bean = new PrimitiveTypesBean(true, (byte)0, (short)400, 'X', 20, 90L, 150.0f, 100.0d);
        assertEquals(bean.toString(), EXPECTED_TO_STRING_PRIMITIVES);
    }

    @Test
    public void should_construct_unified_toString_for_bean_with_collections() {
        final List<String> stringList = Arrays.asList("a", "b", "c");
        final Map<String, String> stringMap = new HashMap<>();
        stringMap.put("key1", "value1");
        stringMap.put("key2", "value2");
        final Set<String> stringSet = new HashSet<>(Arrays.asList("element1", "element2", "element3"));
        final CollectionBean bean = new CollectionBean(stringList, stringMap, stringSet);
        assertEquals(bean.toString(), EXPECTED_TO_STRING_COLLECTIONS);
    }

    @Test
    public void should_construct_unified_toString_for_general_java_bean() {
        final Map<String, String> testMap = new HashMap<>();
        testMap.put("key", "value");
        final CollectionBean collectionBean = new CollectionBean(null, testMap, new HashSet<String>());
        final CompositeBean compositeBean = new CompositeBean(collectionBean, "abc");
        assertEquals(compositeBean.toString(), EXPECTED_TO_STRING_COMPOSITE);
    }

    @Test
    public void should_return_string_containing_null_for_null_input() {
        final String expected = "null";
        final String actual = new BeanToStringBuilder(null).toString();
        assertEquals(actual, expected);
    }
}
