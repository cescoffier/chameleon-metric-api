package org.ow2.chameleon.metric.converters;

/**
 *
 */
public interface ConversionFunction {

    public Number apply(Number number);

    public ConversionFunction inverse();

}
