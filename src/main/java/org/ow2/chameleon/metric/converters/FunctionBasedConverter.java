package org.ow2.chameleon.metric.converters;

import org.ow2.chameleon.metric.Quantity;
import org.ow2.chameleon.metric.Unit;

/**
 * Quantity converter based on a conversion function.
 */
public class FunctionBasedConverter<Q extends Quantity<Q>> implements QuantityConverter<Q> {

    private final Unit<Q> from;
    private final Unit<Q> to;
    private final ConversionFunction conversion;

    public FunctionBasedConverter(Unit<Q> from, Unit<Q> to, ConversionFunction conversion) {
        this.from = from;
        this.to = to;
        this.conversion = conversion;
    }

    public QuantityConverter<Q> inverse() {
        return new FunctionBasedConverter<Q>(to, from, conversion.inverse());
    }

    public Quantity<Q> convert(Quantity<Q> quantity) {
        if (!quantity.getUnit().isCompatible(from)) {
            throw new IllegalArgumentException("The quantity " + quantity.toString() + " cannot be converter to " +
                    to.toString() + " - incompatible units (expected " + from.toString() + ")");
        } else {
            return Quantity.valueOf(conversion.apply(quantity.getNumber()), to);
        }
    }

    public Unit<Q> from() {
        return from;
    }

    public Unit<Q> to() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FunctionBasedConverter that = (FunctionBasedConverter) o;

        return from.equals(that.from) && to.equals(that.to);

    }

    @Override
    public int hashCode() {
        int result = from.hashCode();
        result = 31 * result + to.hashCode();
        return result;
    }
}
