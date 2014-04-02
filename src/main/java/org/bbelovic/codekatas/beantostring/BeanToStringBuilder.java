package org.bbelovic.codekatas.beantostring;

public class BeanToStringBuilder {

    private final ToStringStrategy toStringStrategy;
    private final Object target;

    public BeanToStringBuilder(final Object target) {
        this (target, new ReadMethodToStringStrategy(target));
    }

    public BeanToStringBuilder(final Object target, final ToStringStrategy toStringStrategy) {
        this.target = target;
        this.toStringStrategy = toStringStrategy;
    }

    @Override
    public String toString() {
        return toStringStrategy.toString();
    }

}
