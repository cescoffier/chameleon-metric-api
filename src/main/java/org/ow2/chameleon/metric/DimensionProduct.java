package org.ow2.chameleon.metric;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: clement
 * Date: 11/07/13
 * Time: 18:49
 * To change this template use File | Settings | File Templates.
 */
public class DimensionProduct {


    public static String toString(Map<Dimension, Integer> product) {
        // Consolidate first.
        Map<Dimension, Integer> consolidated = consolidate(product);

        StringBuilder symbol = new StringBuilder();
        for (Map.Entry<Dimension, Integer> entry : consolidated.entrySet()) {
            boolean prependedSymbol = false;
            if (symbol.length() != 0) {
                if (entry.getValue() == -1) {
                    symbol.append("/");
                    prependedSymbol = true;
                } else {
                    symbol.append(".");
                }
            }
            symbol.append(entry.getKey().toString());
            if (! prependedSymbol && entry.getValue() != 1) {
                symbol.append("^").append(entry.getValue());
            }
        }
        return symbol.toString();
    }

    public static Map<Dimension, Integer> consolidate(Map<Dimension, Integer> product) {
        LinkedHashMap<Dimension, Integer> consolidatedProduct = new LinkedHashMap<Dimension, Integer>();

        for (Map.Entry<Dimension, Integer> entry : product.entrySet()) {
            final Map<Dimension, Integer> productDimensions = entry.getKey().getProductDimensions();
            if (productDimensions.size() == 0) {
                // Dimensionless !
                // No impact on anything.
            }

            if (productDimensions.size() == 1) {
                // Fundamental dimension.
                if (consolidatedProduct.containsKey(entry.getKey())) {
                    final int value = consolidatedProduct.get(entry.getKey()) + entry.getValue();
                    if (value == 0) {
                        consolidatedProduct.remove(entry.getKey());
                    } else {
                        consolidatedProduct.put(entry.getKey(),
                                value);
                    }
                } else if (entry.getValue() != 0) {
                    consolidatedProduct.put(entry.getKey(), entry.getValue());
                }
            } else {
                // Non fundamental dimension, must be exploded
                // First consolidate it.
                Map<Dimension, Integer> cons = consolidate(productDimensions);
                for (Map.Entry<Dimension, Integer> consolidatedEntry : cons.entrySet()) {
                    final Dimension dimension = consolidatedEntry.getKey();
                    if (consolidatedProduct.containsKey(dimension)) {
                        final int value = consolidatedProduct.get(dimension) + consolidatedEntry.getValue();
                        if (value == 0) {
                            consolidatedProduct.remove(dimension);
                        } else {
                            consolidatedProduct.put(dimension,
                                    value);
                        }
                    } else {
                        consolidatedProduct.put(dimension, consolidatedEntry.getValue());
                    }
                }
            }
        }

        return consolidatedProduct;
    }

}
