package org.ow2.chameleon.metric.quantities;

import org.junit.Test;
import org.ow2.chameleon.metric.Quantity;
import org.ow2.chameleon.metric.UnitBuilder;
import org.ow2.chameleon.metric.units.Angle;
import org.ow2.chameleon.metric.units.SI;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: clement
 * Date: 12/07/13
 * Time: 09:40
 * To change this template use File | Settings | File Templates.
 */
public class IlluminanceTest {


    @Test
    public void testIlluminance() {
        Illuminance illuminance1 = new Illuminance(10);

        Illuminance illuminance2 = new Illuminance(10, Illuminance.LUX);

        assertThat(illuminance1.getUnit().isCompatible(illuminance2.getUnit()));
        assertThat(illuminance1.getUnit().getDimension()).isEqualTo(illuminance2.getUnit().getDimension());

        Quantity<Illuminance> result = illuminance1.add(illuminance2);
        assertThat(result.getNumber()).isEqualTo(20.0);
        assertThat(result.getUnit()).isEqualTo(Illuminance.LUX);
    }

    @Test
    public void testAddingIlluminanceFromDerivedUnit() {
        Illuminance illuminance1 = new Illuminance(10);

        Quantity<Illuminance> illuminance2 = new Quantity<Illuminance>(10,
                new UnitBuilder<Illuminance>()
                        .from(SI.CANDELA).times(Angle.STERADIAN).pow(SI.METRE, -2)
                        .name("lux")
                        .symbol("lx").build());

        assertThat(illuminance1.getUnit().isCompatible(illuminance2.getUnit()));
        assertThat(illuminance1.getUnit().getDimension()).isEqualTo(illuminance2.getUnit().getDimension());

        Quantity<Illuminance> result = illuminance1.add(illuminance2);
        assertThat(result.getNumber()).isEqualTo(20.0);
        assertThat(result.getUnit()).isEqualTo(Illuminance.LUX);
    }
}
