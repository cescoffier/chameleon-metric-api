package org.ow2.chameleon.metric.converters;

/**
 *
 */
public class Multiplication implements ConversionFunction {

    private final Number constant;


    public Multiplication(Number constant) {
        this.constant = constant;
    }

    public ConversionFunction inverse() {
        return new Multiplication(Math.pow(constant.doubleValue(), -1));
    }

    public Number apply(Number number) {
        return number.doubleValue() * constant.doubleValue();
    }
}
