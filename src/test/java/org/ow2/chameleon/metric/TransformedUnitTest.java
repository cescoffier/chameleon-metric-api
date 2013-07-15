package org.ow2.chameleon.metric;

import org.junit.Test;
import org.ow2.chameleon.metric.quantities.Temperature;
import org.ow2.chameleon.metric.units.SI;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Check transformed units
 */
public class TransformedUnitTest {

    @Test
    public void testCelsius() {
        Unit celsius = new TransformedUnitBuilder<Temperature>(SI.KELVIN).add(-273.15).name("celsius").symbol("ºC")
                .build();
        assertThat(celsius.getDimension()).isEqualTo(Dimension.TEMPERATURE);
        assertThat(celsius.getSymbol()).isEqualTo("ºC");
        assertThat(celsius.getName()).isEqualTo("celsius");
    }

    @Test
    public void testFahrenheit() {
        Unit fahrenheit = new TransformedUnitBuilder<Temperature>(SI.KELVIN).times(9d / 5d).add(-459.67).name("fahrenheit")
                .symbol("ºF")
                .build();
        assertThat(fahrenheit.getDimension()).isEqualTo(Dimension.TEMPERATURE);
        assertThat(fahrenheit.getSymbol()).isEqualTo("ºF");
        assertThat(fahrenheit.getName()).isEqualTo("fahrenheit");
    }

    @Test
    public void testGrade() {
        Unit rad = new UnitBuilder().symbol("rad").name("radian").from(SI.METRE).divide(SI.METRE).build();
        assertThat(rad.getDimension()).isEqualTo(Dimension.NONE);

        Unit grad = new TransformedUnitBuilder(rad).symbol("gr").name("grade").times(Math.PI).times(2d).times
                (Math.pow(400d, -1)).build();

        assertThat(grad.getDimension()).isEqualTo(Dimension.NONE);
        assertThat(grad.getSymbol()).isEqualTo("gr");
    }
}
