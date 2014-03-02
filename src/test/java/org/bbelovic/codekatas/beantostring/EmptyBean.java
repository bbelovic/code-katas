package org.bbelovic.codekatas.beantostring;

public class EmptyBean {
    @Override
    public String toString() {
        return new BeanToStringBuilder(this).toString();
    }
}
