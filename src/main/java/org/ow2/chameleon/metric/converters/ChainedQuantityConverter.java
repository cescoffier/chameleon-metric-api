package org.ow2.chameleon.metric.converters;

import org.ow2.chameleon.metric.Quantity;
import org.ow2.chameleon.metric.Unit;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a chain of conversion.
 */
public class ChainedQuantityConverter<Q extends Quantity<Q>> implements QuantityConverter<Q> {

    private final LinkedList<QuantityConverter<Q>> chain;

    public ChainedQuantityConverter(List<QuantityConverter<Q>> chain) {
        this.chain = new LinkedList<QuantityConverter<Q>>(chain);
    }

    public QuantityConverter<Q> inverse() {
        List<QuantityConverter<Q>> reverse = new LinkedList<QuantityConverter<Q>>(chain);
        Collections.reverse(reverse);
        return new ChainedQuantityConverter<Q>(reverse);
    }

    public Quantity<Q> convert(Quantity<Q> quantity) {
        if (! quantity.unit().equals(from())) {
            throw new IllegalArgumentException("The quantity " + quantity.toString() + " cannot be converter to " +
                    to().toString() + " - incompatible units (expected " + from().toString() + ")");
        }
        // Compose all converters
        Quantity<Q> converted = quantity;
        for (QuantityConverter<Q> converter : chain) {
            converted = converter.convert(converted);
        }
        return converted;
    }

    public Unit<Q> from() {
        if (chain.isEmpty()) {
            return null;
        } else {
            return chain.getFirst().from();
        }
    }

    public Unit<Q> to() {
        if (chain.isEmpty()) {
            return null;
        } else {
            return chain.getLast().to();
        }
    }
}
