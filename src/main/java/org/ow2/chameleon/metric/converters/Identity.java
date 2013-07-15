package org.ow2.chameleon.metric.converters;

/**
 * Identity
 */
public class Identity implements ConversionFunction {


    public static final Identity IDENTITY = new Identity();


    public Number apply(Number number) {
        return number;
    }

    public ConversionFunction inverse() {
        return  this;
    }
}
