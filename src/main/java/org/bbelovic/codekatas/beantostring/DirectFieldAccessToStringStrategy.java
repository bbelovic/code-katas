package org.bbelovic.codekatas.beantostring;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.*;

import static java.lang.String.format;

public class DirectFieldAccessToStringStrategy implements ToStringStrategy {

    private final Object target;

    public DirectFieldAccessToStringStrategy(final Object target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return this.buildToString();
    }

    private String buildToString() {
        if (this.target == null) {
            return "null";
        }
        final StringBuilder sb = new StringBuilder();
        final Collection<Field> fieldsForToString = getFieldsForToString();
        sb.append(target.getClass().getSimpleName());
        sb.append("[");
        final Iterator<Field> it = fieldsForToString.iterator();
        if (it.hasNext()) {
            final Field field = it.next();
            sb.append(format("%s=%s", field.getName(), getFieldValue(field)));
        }
        while (it.hasNext()) {
            final Field field = it.next();
            sb.append(format(", %s=%s", field.getName(), getFieldValue(field)));
        }
        sb.append("]");
        return sb.toString();
    }

    private Object getFieldValue(Field field) {
        try {
            return field.get(this.target);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to get value of field="+ field.getName(), e);
        }
    }

    private Collection<Field> getFieldsForToString() {
        final Field [] fields = target.getClass().getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);
        Arrays.sort(fields, FIELD_COMPARATOR);
        return filterClassFields(Arrays.asList(fields));
    }

    private Collection filterClassFields(final List<Field> fields) {
        final Collection<Field> filteredFields = new ArrayList<>();
        for (final Field each: fields) {
            if (!"class".equals(each.getName()) && !each.isAnnotationPresent(LoggingIgnored.class)) {
                filteredFields.add(each);
            }
        }
        return filteredFields;
    }

    private static final Comparator<Field> FIELD_COMPARATOR =  new Comparator<Field>() {
        @Override
        public int compare(Field o1, Field o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };
}
