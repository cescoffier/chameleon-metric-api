package org.ow2.chameleon.metric.quantities;

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
public class Temperature extends Quantity<Temperature> {


    /**
     * @param number
     * @param unit
     */
    public Temperature(Number number, Unit<Temperature> unit) {
        super(number, unit);
    }

}
