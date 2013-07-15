package org.ow2.chameleon.metric;

/**
 *
 */
public class Unit<Q extends Quantity<Q>> {

    /**
     * Holds the symbol.
     */
    private final String symbol;
    /**
     * Holds the symbol.
     */
    private final String name;
    /**
     * Holds the dimension.
     * If not set {@link Dimension#NONE} is used.
     */
    private final Dimension dimension;

    public Unit(String symbol, String name, Dimension dimension) {
        this.symbol = symbol;
        this.name = name;
        this.dimension = dimension;
    }

    public Unit(String symbol, Dimension dimension) {
        this(symbol, null, dimension);
    }

    public Unit(String symbol, String name) {
        this(symbol, name, Dimension.NONE);
    }

    public Unit(String symbol) {
        this(symbol, null, Dimension.NONE);
    }

    @Override
    public int hashCode() {
        return symbol.hashCode();
    }

    public Dimension getDimension() {
        return dimension;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public boolean isCompatible(Unit<?> that) {
        return this == that
                || getSymbol().equals(that.getSymbol())
                || this.dimension.equals(that.dimension)  && ! this.dimension.equals(Dimension.NONE);
    }


}
