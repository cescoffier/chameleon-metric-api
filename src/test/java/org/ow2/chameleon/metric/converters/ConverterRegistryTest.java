package org.ow2.chameleon.metric.converters;

import org.junit.Test;
import org.ow2.chameleon.metric.Quantity;
import org.ow2.chameleon.metric.Unit;
import org.ow2.chameleon.metric.quantities.Length;
import org.ow2.chameleon.metric.units.SI;

import java.util.LinkedList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 *
 */
public class ConverterRegistryTest {


    @Test
    public void testConversionFromInchToMeter() {
        QuantityConverter<Length> converter = ConverterRegistry.findConverter(Length.INCH, Length.METER);
        assertThat(converter).isNotNull();
        Quantity<Length> converted = converter.convert(Quantity.valueOf(10, Length.INCH));
        assertThat(converted.unit()).isEqualTo(Length.METER);
        assertThat(converted.value()).isEqualTo(0.254);
    }

    @Test
    public void testConversionFromFootToCentimeter() {
        QuantityConverter<Length> converter = ConverterRegistry.findConverter(Length.FOOT, Length.CENTIMETER);
        assertThat(converter).isNotNull();
        Quantity<Length> converted = converter.convert(Quantity.valueOf(10, Length.FOOT));
        assertThat(converted.unit()).isEqualTo(Length.CENTIMETER);
        assertThat(converted.value()).isEqualTo(304.8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectIncorrectInput() {
        QuantityConverter<Length> converter = ConverterRegistry.findConverter(Length.FOOT, Length.CENTIMETER);
        assertThat(converter).isNotNull();
        // Illegal input
        converter.convert(Quantity.valueOf(10, Length.CENTIMETER));
    }
}
