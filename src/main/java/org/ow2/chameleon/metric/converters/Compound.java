package org.ow2.chameleon.metric.converters;

/**
 * Composition of operations.
 */
public class Compound implements ConversionFunction {

    private final ConversionFunction left;
    private final ConversionFunction right;

    public Compound(ConversionFunction left, ConversionFunction right) {
        this.left = left;
        this.right = right;
    }

    public Number apply(Number number) {
        return left.apply(right.apply(number));
    }

    public ConversionFunction inverse() {
        return new Compound(right.inverse(), left.inverse());
    }
}
