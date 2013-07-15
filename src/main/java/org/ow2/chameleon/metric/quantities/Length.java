package org.ow2.chameleon.metric.quantities;

import org.ow2.chameleon.metric.*;
import org.ow2.chameleon.metric.units.Angle;
import org.ow2.chameleon.metric.units.SI;

/**
 * Created with IntelliJ IDEA.
 * User: clement
 * Date: 12/07/13
 * Time: 09:33
 * To change this template use File | Settings | File Templates.
 */
public class Length extends Quantity<Length> {

    public static final Unit<Length> METER = SI.METRE;

    public static final Unit<Length> KILOMETER = new TransformedUnitBuilder<Length>(METER)
            .times(1000)
            .symbol("km")
            .registerConverter()
            .build();

    public static final Unit<Length> HECTOMETER = new TransformedUnitBuilder<Length>(METER)
            .times(100)
            .symbol("hm")
            .registerConverter()
            .build();

    public static final Unit<Length> DECIMETER = new TransformedUnitBuilder<Length>(METER)
            .times(0.1)
            .symbol("dm")
            .registerConverter()
            .build();

    public static final Unit<Length> CENTIMETER = new TransformedUnitBuilder<Length>(METER)
            .times(0.01)
            .symbol("cm")
            .registerConverter()
            .build();

    public static final Unit<Length> MILLIMETER = new TransformedUnitBuilder<Length>(METER)
            .times(0.001)
            .symbol("mm")
            .registerConverter()
            .build();

    public static final Unit<Length> INCH = new TransformedUnitBuilder<Length>(CENTIMETER)
            .times(2.54)
            .symbol("in")
            .registerConverter()
            .build();

    public static final Unit<Length> FOOT = new TransformedUnitBuilder<Length>(INCH)
            .times(12)
            .symbol("ft")
            .registerConverter()
            .build();

    /**
     * @param number
     * @param unit
     */
    public Length(Number number, Unit<Length> unit) {
        super(number, unit);
    }

    public Length(Quantity<Length> q) {
        super(q.value(), q.unit());
    }

    public Length(Number number) {
        super(number, METER);
    }

    public Length add(Length that) {
        Quantity<Length> l = this.add((Quantity<Length>) that);
        return new Length(l);
    }

    public Length sub(Length that) {
        Length s = new Length(-1 * that.value().doubleValue(), that.unit());
        return this.add(s);
    }
}
