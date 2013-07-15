package org.ow2.chameleon.metric.converters;

/**
 * Addition
 */
public class Addition implements ConversionFunction {

    private final Number offset;

    public Addition(Number offset) {
        this.offset = offset;
    }

    public ConversionFunction inverse() {
        return new Addition(-1 * offset.doubleValue());
    }

    public Number apply(Number number) {
        return number.doubleValue() + offset.doubleValue();
    }
}
