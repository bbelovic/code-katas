package org.bbelovic.codekatas.beantostring;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class BeanToStringBuilder {

    private final Object target;

    public BeanToStringBuilder(final Object target) {
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
        final PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors();
            sb.append("[");
            for (int i = 0; i < propertyDescriptors.length; i++) {
                final PropertyDescriptor each = propertyDescriptors[i];
                if (canIncludeInToString(each)) {
                    includeInToString(each, sb, i != propertyDescriptors.length - 1);
                }
            }
            sb.append("]");
        return sb.toString();
    }

    private PropertyDescriptor[] getPropertyDescriptors() {
        final BeanInfo beanInfo = getBeanInfo();
        if (beanInfo == null) {
            return new PropertyDescriptor[0];
        }
        Arrays.asList(beanInfo.getPropertyDescriptors());
        return beanInfo.getPropertyDescriptors();
    }

    private BeanInfo getBeanInfo() {
        try {
            return Introspector.getBeanInfo(target.getClass());
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object invokeReadMethod(final Method readMethod) {
        // TODO may return null
        Object result = null;
        try {
            result = readMethod.invoke(target);
        } catch (final IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean canIncludeInToString(final PropertyDescriptor propertyDescriptor) {
        // TODO handle possible nulls
        final Method method = propertyDescriptor.getReadMethod();
        final String displayName = propertyDescriptor.getDisplayName();
        return !"class".equals(displayName) && !method.isAnnotationPresent(LoggingIgnored.class);
    }

    private void includeInToString(final PropertyDescriptor descriptor, final StringBuilder sb, boolean appendDelimiter) {
        final Method readMethod = descriptor.getReadMethod();
        final String displayName = descriptor.getDisplayName();
        final String result = displayName + "=" + invokeReadMethod(readMethod) + (appendDelimiter ? ", ": "");
        sb.append(result);
    }
}
