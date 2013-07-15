package org.ow2.chameleon.metric;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Creates base unit
 */
public class UnitBuilder<Q extends Quantity<Q>> {

    private Map<Unit<?>, Integer> products = new LinkedHashMap<Unit<?>, Integer>();
    private String name;
    private String symbol;

    public UnitBuilder<Q> from(Unit<?> unit) {
        products.put(unit, 1);
        return this;
    }

    public UnitBuilder<Q> symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public UnitBuilder<Q> name(String name) {
        this.name = name;
        return this;
    }

    public UnitBuilder<Q> times(Unit<?> unit) {
        return pow(unit, 1);
    }

    public UnitBuilder<Q> divide(Unit<?> unit) {
        if (products.containsKey(unit)) {
            products.put(unit, products.get(unit) - 1);
        } else {
            products.put(unit, -1);
        }
        return this;
    }

    public UnitBuilder<Q> pow(Unit<?> unit, int n) {
        if (products.containsKey(unit)) {
            products.put(unit, products.get(unit) + n);
        } else {
            products.put(unit, n);
        }
        return this;
    }

    public Unit<Q> build() {
        if (products.isEmpty()) {
            return new Unit<Q>(symbol, name);
        }
        products = UnitProduct.consolidate(products);
        return new DerivedUnit<Q>(symbol, name, products);
    }
}
