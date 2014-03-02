package org.bbelovic.codekatas.beantostring;

class PrimitiveTypesBean {

    private final boolean boolField;
    private final byte byteField;
    private final short shortField;
    private final char charField;
    private final int intField;
    private final long longField;
    private final float floatField;
    private final double doubleField;

    public PrimitiveTypesBean(boolean boolField, byte byteField, short shortField, char charField,
                              int intField, long longField, float floatField, double doubleField) {
        this.boolField = boolField;
        this.byteField = byteField;
        this.shortField = shortField;
        this.charField = charField;
        this.intField = intField;
        this.longField = longField;
        this.floatField = floatField;
        this.doubleField = doubleField;
    }

    public boolean isBoolField() {
        return boolField;
    }

    public byte getByteField() {
        return byteField;
    }

    public short getShortField() {
        return shortField;
    }

    public char getCharField() {
        return charField;
    }

    public int getIntField() {
        return intField;
    }

    public long getLongField() {
        return longField;
    }

    public float getFloatField() {
        return floatField;
    }

    public double getDoubleField() {
        return doubleField;
    }

    @Override
    public String toString() {
        return new BeanToStringBuilder(this).toString();
    }
}
