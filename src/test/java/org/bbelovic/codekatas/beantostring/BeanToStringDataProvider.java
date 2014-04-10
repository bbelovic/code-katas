package org.bbelovic.codekatas.beantostring;

import org.testng.annotations.DataProvider;

import java.util.*;

import static java.util.Arrays.asList;

public class BeanToStringDataProvider {

    private static final PrimitiveTypesBean PRIMITIVE_TYPES_BEAN = new PrimitiveTypesBean(true, (byte)0, (short)400, 'X', 20, 90L, 150.0f, 100.0d);
    private static final CollectionBean COLLECTION_BEAN = createCollectionBean();
    private static final ConfidentialData CONFIDENTIAL_DATA_BEAN = new ConfidentialData("jdoe", "abcd", "123", "jdoe@company.com");
    private static final CompositeBean COMPOSITE_BEAN = createCompositeBean();
    private static final ArrayBean ARRAY_BEAN = createArrayBean();

    private static final EmptyBean EMPTY_BEAN = new EmptyBean();

    @DataProvider(name = "toStringStrategyDataProvider")
    public static Object [][] toStringStrategyDataProvider() {
        return new Object [][] {
                {new DirectFieldAccessToStringStrategy(EMPTY_BEAN), EmptyBean.class.getSimpleName(), new Object[0]},

                {new DirectFieldAccessToStringStrategy(PRIMITIVE_TYPES_BEAN), PrimitiveTypesBean.class.getSimpleName(),
                        preparePrimitiveTypesData()},

                {new DirectFieldAccessToStringStrategy(CONFIDENTIAL_DATA_BEAN), ConfidentialData.class.getSimpleName(),
                        prepareConfidentialBeanData()},

                {new DirectFieldAccessToStringStrategy(COLLECTION_BEAN), CollectionBean.class.getSimpleName(),
                        prepareCollectionBeanData()},

                {new DirectFieldAccessToStringStrategy(COMPOSITE_BEAN), CompositeBean.class.getSimpleName(),
                        prepareCompositeBeanData()},

                {new DirectFieldAccessToStringStrategy(ARRAY_BEAN), ArrayBean.class.getSimpleName(),
                        prepareArrayBeanData()},

                {new ReadMethodToStringStrategy(EMPTY_BEAN), EmptyBean.class.getSimpleName(), new Object[0]},

                {new ReadMethodToStringStrategy(PRIMITIVE_TYPES_BEAN), PrimitiveTypesBean.class.getSimpleName(),
                        preparePrimitiveTypesData()},

                {new ReadMethodToStringStrategy(CONFIDENTIAL_DATA_BEAN), ConfidentialData.class.getSimpleName(),
                        prepareConfidentialBeanData()},

                {new ReadMethodToStringStrategy(COLLECTION_BEAN), CollectionBean.class.getSimpleName(),
                        prepareCollectionBeanData()},

                {new ReadMethodToStringStrategy(COMPOSITE_BEAN), CompositeBean.class.getSimpleName(),
                        prepareCompositeBeanData()},

                {new ReadMethodToStringStrategy(ARRAY_BEAN), ArrayBean.class.getSimpleName(),
                        prepareArrayBeanData()}
        };
    }

    @DataProvider(name = "beanAndToStringContentsDataProvider")
    public static Object [][] beanAndToStringContentsDataProvider() {
        return new Object [][] {{EMPTY_BEAN, new String[0]},
                {PRIMITIVE_TYPES_BEAN, preparePrimitiveTypesData()},
                {COLLECTION_BEAN, prepareCollectionBeanData()},
                {CONFIDENTIAL_DATA_BEAN, prepareConfidentialBeanData()},
                {COMPOSITE_BEAN, prepareCompositeBeanData()}
        };

    }

    private static CollectionBean createCollectionBean() {
        final List<String> stringList = asList("a", "b", "c");
        final Map<String, String> stringMap = new HashMap<>();
        stringMap.put("key1", "value1");
        stringMap.put("key2", "value2");
        final Set<String> stringSet = new HashSet<>(asList("element1", "element2", "element3"));
        return new CollectionBean(stringList, stringMap, stringSet);
    }
    private static ArrayBean createArrayBean() {
        return new ArrayBean(new int [] {1}, new int [][] {{0}, {1}}, new Object[0], new Object[][] {{"A"}}, null);
    }

    private static CompositeBean createCompositeBean() {
        return new CompositeBean(createCollectionBean(), "abc");
    }

    private static Object[] prepareCompositeBeanData() {
        return new Object[] {"collectionBean",createCollectionBean(), "stringField", "abc"};
    }

    private static Object[] prepareCollectionBeanData() {
        return new Object[] {"stringList", asList("a", "b", "c"),
                "stringMap", mapOf(new String [] {"key1", "key2"}, new String[] {"value1", "value2"}),
                "stringSet", setOf("element1", "element2", "element3")};
    }

    private static Object[] prepareConfidentialBeanData() {
        return new Object[]{"username", "jdoe", "email", "jdoe@company.com"};
    }

    private static Object[] preparePrimitiveTypesData() {
        return new Object[] {"boolField", "true", "byteField", "0", "shortField", "400", "charField", "X",
                "intField", "20", "longField", "90", "floatField", "150.0", "doubleField", "100.0"};
    }

    private static Object [] prepareArrayBeanData() {
        return new Object[] {"intArr", Arrays.toString(ARRAY_BEAN.getIntArr()),
                             "intMatrix", Arrays.deepToString(ARRAY_BEAN.getIntMatrix()),
                             "objectArr", Arrays.toString(ARRAY_BEAN.getObjectArr()),
                             "objectMatrix", Arrays.deepToString(ARRAY_BEAN.getObjectMatrix()),
                             "someObject", ARRAY_BEAN.getSomeObject()};
    }

    private static <T> Set<T> setOf(final T... elements) {
        return new HashSet<>(asList(elements));
    }

    private static <K,V> Map<K,V> mapOf(final K[] keys, final V[] values) {
        final Map<K, V> result = new HashMap<>();
        if (keys.length == values.length) {
            for (int i = 0; i < keys.length; i++) {
                result.put(keys[i], values[i]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        CompositeBean cp = createCompositeBean();
        System.out.println(cp);
    }
}
