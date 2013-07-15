package org.ow2.chameleon.metric;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: clement
 * Date: 11/07/13
 * Time: 17:58
 * To change this template use File | Settings | File Templates.
 */
public class DimensionBuilder {


    private Map<Dimension, Integer> products = new LinkedHashMap<Dimension, Integer>();

    public DimensionBuilder from(Dimension dimension) {
        products.put(dimension, 1);
        return this;
    }

    public DimensionBuilder times(Dimension dimension) {
        return pow(dimension, 1);
    }

    public DimensionBuilder divide(Dimension dimension) {
        if (products.containsKey(dimension)) {
            products.put(dimension, products.get(dimension) - 1);
        } else {
            products.put(dimension, -1);
        }
        return this;
    }

    public DimensionBuilder pow(Dimension dimension, int n) {
        if (products.containsKey(dimension)) {
            products.put(dimension, products.get(dimension) + n);
        } else {
            products.put(dimension, n);
        }
        return this;
    }

    public Dimension build() {
        products = DimensionProduct.consolidate(products);
        return new Dimension(products);
    }

}
