package org.ow2.chameleon.metric;

import org.ow2.chameleon.metric.converters.ConverterRegistry;
import org.ow2.chameleon.metric.converters.QuantityConverter;
import org.ow2.chameleon.metric.quantities.Length;

/**
 * Created with IntelliJ IDEA.
 * User: clement
 * Date: 11/07/13
 * Time: 17:28
 * To change this template use File | Settings | File Templates.
 */
public class Quantity<Q extends Quantity<Q>> {

    private final Number number;
    private final Unit<Q> unit;

    /**
     * @param number
     * @param unit
     */
    public Quantity(Number number, Unit<Q> unit) {
        this.number = number;
        this.unit = unit;
    }

    /**
     * Returns the amount corresponding to the specified value and unit.
     *
     * @param value the value stated in the specified unit.
     * @param unit  the unit in which the value is stated.
     * @return the corresponding amount.
     */
    public static <Q extends Quantity<Q>> Quantity<Q> valueOf(Number value,
                                                              Unit<Q> unit) {
        return new Quantity<Q>(value, unit);
    }

    public Number value() {
        return getNumber();
    }

    public Number getNumber() {
        return number;
    }

    public Unit<Q> unit() {
        return getUnit();
    }

    public Unit<Q> getUnit() {
        return unit;
    }

    public Quantity<Q> add(Quantity<Q> that) {
        if (this.getUnit().isCompatible(that.getUnit())) {
            // Units are compatible, but we still must use the normalized quantity.
            return valueOf(that.getNormalizedQuantity().getNumber().doubleValue() + this.getNormalizedQuantity().getNumber
                    ().doubleValue
                    (), unit);
        } else {
           throw new IncommensurableException("Cannot add " + this.toString() + " and " + that.toString() + " - " +
                   "units are incompatible");
        }
    }

    public Quantity<Q> times(Number number) {
        return valueOf(getNumber().doubleValue() * number.doubleValue(), getUnit());
    }

    public Quantity<Q> getNormalizedQuantity() {
        if (unit instanceof TransformedUnit) {
            TransformedUnit<Q> transformedUnit = (TransformedUnit<Q>) getUnit();
            Quantity<Q> converted = transformedUnit.getConverter().convert(this);
            return converted.getNormalizedQuantity();
        } else {
            return this;
        }
    }

    public String toString() {
        return value() + unit().toString();
    }

    public Quantity<Q> as(Unit<Q> unit) {
        // Retrieve the normalized quantity
        Quantity<Q> normalized = getNormalizedQuantity();
        QuantityConverter<Q> converter = ConverterRegistry.findConverter(normalized.unit, unit);

        if (converter == null) {
            throw new IllegalArgumentException("Cannot convert " + this + " to " + unit + " - no converter in the " +
                    "registry");
        } else {
            return converter.convert(normalized);
        }
    }

}
