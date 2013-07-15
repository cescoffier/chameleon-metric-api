package org.ow2.chameleon.metric;

import org.ow2.chameleon.metric.converters.ConversionFunction;
import org.ow2.chameleon.metric.converters.FunctionBasedConverter;
import org.ow2.chameleon.metric.converters.QuantityConverter;

/**
 *
 */
public class TransformedUnit<Q extends Quantity<Q>> extends Unit<Q> {

    private final QuantityConverter<Q> converter;

    public TransformedUnit(String symbol, String name, Unit<Q> parent, ConversionFunction conversion) {
        this(symbol, name, parent, conversion, parent.getDimension());
    }

    public TransformedUnit(String symbol, String name, Unit<Q> parent, ConversionFunction conversion,
                           Dimension dimension) {
        super(symbol, name, dimension);
        this.converter = new FunctionBasedConverter<Q>(this, parent, conversion);
    }

    @Override
    public String toString() {
        return getSymbol();
    }

    public QuantityConverter<Q> getConverter() {
        return converter;
    }
}
