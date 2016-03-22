package pl.edu.pk.aipsc.math;

import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mslosarz
 *         <p>
 *         Reprezentuje wielomian w postaci (a1*x - b1)*(a2*x - b2)*...
 */
public class Polynomial implements Function {

    private List<Function> numerators = new ArrayList<>();
    private List<Function> denominators = new ArrayList<>();

    private Polynomial() {

    }

    public Polynomial mul(Function coefficient) {
        numerators.add(coefficient);
        return this;
    }

    public Polynomial div(Function coefficient) {
        denominators.add(coefficient);
        return this;
    }

    @Override
    public Complex calculate(Complex actual) {
        if (numerators.isEmpty() && denominators.isEmpty()) {
            return actual;
        }

        Complex mulResult = Complex.ONE;
        for (Function factories : numerators) {
            mulResult = mulResult.multiply(factories.calculate(actual));
        }

        Complex divResult = Complex.ONE;
        for (Function factories : denominators) {
            divResult = divResult.multiply(factories.calculate(actual));
        }
        if (divResult.equals(Complex.ZERO)) {
            return new Complex(Double.MAX_VALUE);
        }

        return mulResult.divide(divResult);
    }

    public void removeFromNumerator(Function f) {
        numerators.remove(f);
    }

    public void removeAll(Function f) {
        while (numerators.remove(f)) ;
        while (denominators.remove(f)) ;
    }

    public void removeAllFromNumerator(Function f) {
        while (numerators.remove(f)) ;
    }

    public void removeFromDenominator(Function f) {
        denominators.remove(f);
    }

    public void removeAllFromDenominator(Function f) {
        while (denominators.remove(f)) ;
    }

    public static Polynomial polynomial() {
        return new Polynomial();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(createMulModel(numerators));
        builder.append("/");
        builder.append(createMulModel(denominators));
        return builder.toString();
    }

    /**
     * tworzy (a*x + b) * ...
     */
    private StringBuilder createMulModel(List<Function> functions) {
        StringBuilder builder = new StringBuilder();
        if (functions.isEmpty()) {
            return builder.append("1");
        }

        builder.append("(");
        for (int i = 0; i < functions.size(); i++) {
            builder.append(functions.get(i));
            if (i != functions.size() - 1) {
                builder.append("*");
            }
        }
        builder.append(")");
        return builder;
    }
}
