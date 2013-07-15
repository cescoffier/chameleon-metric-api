package org.ow2.chameleon.metric;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
public class DerivedUnit<Q extends Quantity<Q>> extends Unit<Q> {


    private final Map<Unit<?>, Integer> product;

    public DerivedUnit(String symbol, String name, Map<Unit<?>, Integer> product) {
        super(symbol, name, UnitProduct.getDimension(product));
        this.product = product;
    }

    public DerivedUnit(String symbol, Map<Unit<?>, Integer> product) {
        this(symbol, null, product);
    }

    public DerivedUnit(Map<Unit<?>, Integer> product) {
        this(UnitProduct.toString(product), null, product);
    }

    @Override
    public String toString() {
        if (getSymbol() != null) {
            return getSymbol();
        }
        return getProductAsString();
    }

    public String getProductAsString() {
        return UnitProduct.toString(product);
    }

    public Map<Unit<?>, Integer> getProduct() {
        return new LinkedHashMap<Unit<?>, Integer>(product);
    }


}
