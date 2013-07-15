package org.ow2.chameleon.metric;

import org.ow2.chameleon.metric.converters.*;

/**
 * Creates base unit
 */
public class TransformedUnitBuilder<Q extends Quantity<Q>> {

    private String name;
    private String symbol;

    private Unit<Q> from;

    private ConversionFunction conversion = Identity.IDENTITY;
    private boolean registerConverter = false;

    public TransformedUnitBuilder(Unit<Q> from) {
        this.from = from;
    }

    public TransformedUnitBuilder<Q> symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public TransformedUnitBuilder<Q> name(String name) {
        this.name = name;
        return this;
    }

    public Unit<Q> build() {
        if (symbol == null) {
            throw new IllegalArgumentException("An unit must have a symbol");
        }

        if (conversion == Identity.IDENTITY) {
            return new Unit<Q>(symbol, name);
        } else {
            // Transformed unit
            Unit<Q> unit =  new TransformedUnit<Q>(symbol, name, from, conversion);
            if (registerConverter) {
                ConverterRegistry.addConverter(new FunctionBasedConverter<Q>(unit, from, conversion));
            }
            return unit;
        }
    }

    public TransformedUnitBuilder<Q> add(Number constant) {
        conversion = new Compound(conversion, new Addition(constant));
        return this;
    }

    public TransformedUnitBuilder<Q> times(Number constant) {
        conversion = new Compound(conversion, new Multiplication(constant));
        return this;
    }

    public TransformedUnitBuilder<Q> registerConverter() {
        registerConverter = true;
        return this;
    }
}
