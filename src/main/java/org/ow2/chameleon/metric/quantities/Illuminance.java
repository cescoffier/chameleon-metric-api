package org.ow2.chameleon.metric.quantities;

import org.ow2.chameleon.metric.IncommensurableException;
import org.ow2.chameleon.metric.Quantity;
import org.ow2.chameleon.metric.Unit;
import org.ow2.chameleon.metric.UnitBuilder;
import org.ow2.chameleon.metric.units.Angle;
import org.ow2.chameleon.metric.units.SI;

/**
 * Created with IntelliJ IDEA.
 * User: clement
 * Date: 12/07/13
 * Time: 09:33
 * To change this template use File | Settings | File Templates.
 */
public class Illuminance extends Quantity<Illuminance> {

    public static final Unit<Illuminance> LUX = new UnitBuilder<Illuminance>()
            .from(SI.CANDELA).times(Angle.STERADIAN).pow(SI.METRE, -2)
            .name("lux")
            .symbol("lx")
            .build();

    /**
     * @param number
     * @param unit
     */
    public Illuminance(Number number, Unit<Illuminance> unit) {
        super(number, unit);
    }

    public Illuminance(Number number) {
        super(number, LUX);
    }


}
