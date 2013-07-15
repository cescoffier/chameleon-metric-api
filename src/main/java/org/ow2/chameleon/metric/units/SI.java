package org.ow2.chameleon.metric.units;

import org.ow2.chameleon.metric.Dimension;
import org.ow2.chameleon.metric.Unit;
import org.ow2.chameleon.metric.quantities.Length;
import org.ow2.chameleon.metric.quantities.Temperature;

/**
 * Created with IntelliJ IDEA.
 * User: clement
 * Date: 11/07/13
 * Time: 21:02
 * To change this template use File | Settings | File Templates.
 */
public class SI {

    public static Unit<Length> METRE = new Unit<Length>("m", "meter", Dimension.LENGTH);

    public static Unit GRAM = new Unit("g", "gram", Dimension.MASS);

    public static Unit SECOND = new Unit("s", "second", Dimension.TIME);

    public static Unit AMPERE = new Unit("A", "ampere", Dimension.ELECTRIC_CURRENT);

    public static Unit<Temperature> KELVIN = new Unit<Temperature>("K", "kelvin", Dimension.TEMPERATURE);

    public static Unit MOL = new Unit("mol", "mol", Dimension.AMOUNT_OF_SUBSTANCE);

    public static Unit CANDELA = new Unit("cd", "candela", Dimension.LUMINOUS_INTENSITY);



}
