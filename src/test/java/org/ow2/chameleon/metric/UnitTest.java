package org.ow2.chameleon.metric;


import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Check base unit.
 */
public class UnitTest {

    @Test
    public void testUnitCreation() {
        Unit unit = new Unit("Hz", new DimensionBuilder().pow(Dimension.TIME, -1).build());
        assertThat(unit.getSymbol()).isEqualTo("Hz");
        assertThat(unit.getDimension().toString()).isEqualTo("T^-1");
        assertThat(unit.getName()).isNull();

        Unit freq = new Unit("Hz", "frequency", new DimensionBuilder().pow(Dimension.TIME, -1).build());
        assertThat(freq.getSymbol()).isEqualTo("Hz");
        assertThat(freq.getDimension().toString()).isEqualTo("T^-1");
        assertThat(freq.getName()).isEqualTo("frequency");

        Unit angle = new Unit("º", "degree");

        assertThat(unit.isCompatible(freq)).isTrue();
        assertThat(freq.isCompatible(angle)).isFalse();
    }
}
