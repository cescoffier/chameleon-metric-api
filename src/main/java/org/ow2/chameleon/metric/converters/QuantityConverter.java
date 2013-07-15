package org.ow2.chameleon.metric.converters;

import org.ow2.chameleon.metric.Quantity;
import org.ow2.chameleon.metric.Unit;

/**
 * Unit converter.
 * Equals must be implemented correctly.
 */
public interface QuantityConverter<Q extends Quantity<Q>> {


    /**
     * Returns the inverse of this converter. If <code>x</code> is a valid
     * value, then <code>x == inverse().convert(convert(x))</code> to within
     * the accuracy of computer arithmetic.
     *
     * @return the inverse of this converter.
     */
    public QuantityConverter<Q> inverse();

    public Quantity<Q> convert(Quantity<Q> quantity);

    public Unit<Q> from();

    public Unit<Q> to();

}
