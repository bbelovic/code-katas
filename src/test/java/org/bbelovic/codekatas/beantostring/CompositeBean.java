package org.bbelovic.codekatas.beantostring;

class CompositeBean {
    private final CollectionBean collectionBean;
    private final String stringField;

    public CompositeBean(final CollectionBean collectionBean, final String stringField) {
        this.collectionBean = collectionBean;
        this.stringField = stringField;
    }

    public CollectionBean getCollectionBean() {
        return collectionBean;
    }

    public String getStringField() {
        return stringField;
    }

    @Override
    public String toString() {
        return new BeanToStringBuilder(this).toString();
    }
}
