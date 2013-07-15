package org.ow2.chameleon.metric;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Dimension {

    /**
     * Holds dimensionless.
     */
    public static final Dimension NONE = new Dimension((char) 0);

    /**
     * Holds length dimension (L).
     */
    public static final Dimension LENGTH = new Dimension('L');

    /**
     * Holds mass dimension (M).
     */
    public static final Dimension MASS = new Dimension('M');

    /**
     * Holds time dimension (T).
     */
    public static final Dimension TIME = new Dimension('T');

    /**
     * Holds electric current dimension (I).
     */
    public static final Dimension ELECTRIC_CURRENT = new Dimension('I');

    /**
     * Holds temperature dimension (Θ).
     */
    public static final Dimension TEMPERATURE = new Dimension('Θ');

    /**
     * Holds amount of substance dimension (N).
     */
    public static final Dimension AMOUNT_OF_SUBSTANCE = new Dimension('N');

    /**
     * Holds luminous intensity dimension (J).
     */
    public static final Dimension LUMINOUS_INTENSITY = new Dimension('J');

    /**
     * Holds the product of dimension.
     */
    private final Map<Dimension, Integer> product;

    /**
     * Holds the dimension symbol.
     * Only <em>fundamental</em> dimension have a symbol.
     */
    private final char symbol;

    /**
     * Creates a new dimension associated to the specified symbol.
     * Must be used only for <em>fundamental</em> dimension.
     *
     * @param symbol
     *            the associated symbol.
     */
    public Dimension(char symbol) {
        this.symbol = symbol;
        this.product = new TreeMap<Dimension, Integer>();
        if (symbol != 0) {
            product.put(this, 1);
        }
    }

    /**
     * Creates a dimension based on the given products of dimension.
     *
     * @param product
     *            the product of dimension
     */
    public Dimension(Map<Dimension, Integer> product) {
        this.product = product;
        this.symbol = 0;
    }

    /**
     * Returns the fundamental dimensions and their exponent whose product is
     * this dimension or <code>null</code> if this dimension is a fundamental
     * dimension.
     *
     * @return the mapping between the fundamental dimensions and their
     *         exponent.
     */
    public Map<Dimension, Integer> getProductDimensions() {
        return new LinkedHashMap<Dimension, Integer>(product);
    }

    /**
     * Returns the representation of this dimension.
     *
     * @return the representation of this dimension.
     */
    @Override
    public String toString() {
        if (symbol != 0) {
            return Character.toString(symbol);
        } else {
            return DimensionProduct.toString(product);
        }

    }

    /**
     * Indicates if the specified dimension is equals to the one specified.
     *
     * @param that
     *            the object to compare to.
     * @return <code>true</code> if this dimension is equals to that dimension;
     *         <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object that) {
        return this == that ||
                that instanceof Dimension
                && DimensionProduct.toString(this.getProductDimensions())
                        .equals(DimensionProduct.toString(((Dimension) that).getProductDimensions()));
    }

    /**
     * Returns the hash code for this dimension.
     *
     * @return this dimension hash code value.
     */
    @Override
    public int hashCode() {
       if (symbol != 0) {
           return Character.valueOf(symbol).hashCode();
       } else {
           return product.hashCode();
       }
    }

}
