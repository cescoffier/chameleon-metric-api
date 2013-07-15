package org.ow2.chameleon.metric;

import org.junit.Test;
import org.ow2.chameleon.metric.quantities.Illuminance;
import org.ow2.chameleon.metric.units.Angle;
import org.ow2.chameleon.metric.units.SI;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Check derived units
 */
public class DerivedUnitTest {

    @Test
    public void testNewton() {
        Unit newton = new UnitBuilder().name("newton").symbol("N").from(SI.GRAM).times(SI.METRE).pow(SI.SECOND,
                -2).build();

        assertThat(newton.getName()).isEqualTo("newton");
        assertThat(newton.getSymbol()).isEqualTo("N");
        assertThat(newton.getDimension()).isEqualTo(new DimensionBuilder().from(Dimension.MASS).times(Dimension
                .LENGTH).pow(Dimension.TIME, -2).build());
        assertThat(newton.toString()).isEqualTo("N");
        assertThat(((DerivedUnit)newton).getProductAsString()).isEqualTo("g.m.s^-2");
    }

    @Test
    public void testJouleAndPascal() {
        Unit newton = new UnitBuilder().name("newton").symbol("N").from(SI.GRAM).times(SI.METRE).pow(SI.SECOND,
                -2).build();

        Unit joule = new UnitBuilder().name("joule").symbol("J").from(newton).times(SI.METRE).build();

        assertThat(joule.getDimension()).isEqualTo(new DimensionBuilder().from(Dimension.MASS).times(Dimension
                .LENGTH).pow(Dimension.TIME, -2).times(Dimension.LENGTH).build());

        //N.m-2, J.m-3

        Unit pascal = new UnitBuilder().name("pascal").symbol("Pa").from(newton).pow(SI.METRE, -2).build();
        Unit pascal_alt = new UnitBuilder().name("pascal").symbol("Pa").from(joule).pow(SI.METRE, -3).build();

        assertThat(pascal.getDimension()).isEqualTo(pascal_alt.getDimension());

    }

    @Test
    public void testLux() {
        Unit<Illuminance> LUX = new UnitBuilder<Illuminance>().from(SI.CANDELA).times(Angle.STERADIAN).pow(SI.METRE,
                -2).build();

        assertThat(LUX.getDimension()).isEqualTo(new DimensionBuilder().from(Dimension.LUMINOUS_INTENSITY).pow
                (Dimension.LENGTH, -2).build());
    }
}
