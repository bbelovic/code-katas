package org.bbelovic.codekatas.beantostring;

import java.util.List;
import java.util.Map;
import java.util.Set;

class CollectionBean {

    private final List<String> stringList;
    private final Map<String, String> stringMap;
    private final Set<String> stringSet;

    public CollectionBean(List<String> stringList, Map<String, String> stringMap, Set<String> stringSet) {
        this.stringList = stringList;
        this.stringMap = stringMap;
        this.stringSet = stringSet;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public Map<String, String> getStringMap() {
        return stringMap;
    }

    public Set<String> getStringSet() {
        return stringSet;
    }

    @Override
    public String toString() {
        return new BeanToStringBuilder(this).toString();
    }
}
