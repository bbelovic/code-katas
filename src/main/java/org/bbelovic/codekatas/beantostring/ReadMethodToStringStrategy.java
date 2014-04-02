package org.bbelovic.codekatas.beantostring;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.String.format;

public class ReadMethodToStringStrategy implements ToStringStrategy {

    private final Object target;

    public ReadMethodToStringStrategy(final Object target) {
        this.target = target;
    }

    @Override
    public String toString() {
        if (target == null) {
            return "null";
        }
        final StringBuilder sb = new StringBuilder();
        final String simpleName = target.getClass().getSimpleName();
        sb.append(simpleName);
        buildToString(sb, getPropertyDescriptors());
        return sb.toString();
    }

    private void buildToString(StringBuilder sb, PropertyDescriptor[] propertyDescriptors) {
        sb.append("[");
        for (int i = 0; i < propertyDescriptors.length; i++) {
            final PropertyDescriptor each = propertyDescriptors[i];
            if (canIncludeInToString(each)) {
                includeInToString(each, sb, i != propertyDescriptors.length - 1);
            }
        }
        sb.append("]");
    }

    private PropertyDescriptor[] getPropertyDescriptors() {
        final BeanInfo beanInfo = getBeanInfo();
        if (beanInfo == null || beanInfo.getPropertyDescriptors() == null) {
            return new PropertyDescriptor[0];
        }
        return beanInfo.getPropertyDescriptors();
    }

    private BeanInfo getBeanInfo() {
        try {
            return Introspector.getBeanInfo(target.getClass());
        } catch (final IntrospectionException e) {
            throw new RuntimeException(format("Unable to introspect class=%s", target.getClass()), e);
        }
    }

    private boolean canIncludeInToString(final PropertyDescriptor propertyDescriptor) {
        final Method method = propertyDescriptor.getReadMethod();
        if (method == null) {
            return false;
        }
        final String displayName = propertyDescriptor.getDisplayName();
        return !"class".equals(displayName) && !method.isAnnotationPresent(LoggingIgnored.class);
    }

    private void includeInToString(final PropertyDescriptor descriptor, final StringBuilder sb, boolean appendDelimiter) {
        final Method readMethod = descriptor.getReadMethod();
        final String displayName = descriptor.getDisplayName();
        final String result = displayName + "=" + invokeReadMethod(readMethod) + (appendDelimiter ? ", ": "");
        sb.append(result);
    }

    /* Invokes method reflectively and returns result.
     * Provided method object is guaranteed not to be null.
     * Null check is done in canIncludeInToString method which
     * precedes invocation of this method
     */
    private Object invokeReadMethod(final Method readMethod) {
        Object result;
        try {
            result = readMethod.invoke(target);
        } catch (final IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Unable to invoke read method", e);
        }
        return result;
    }
}
