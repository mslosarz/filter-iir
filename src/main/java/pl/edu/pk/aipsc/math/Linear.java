package pl.edu.pk.aipsc.math;

import org.apache.commons.math3.complex.Complex;

public class Linear implements Function {
    private Complex a = Complex.ONE;
    private Complex b = Complex.ZERO;

    private Linear(Complex a, Complex b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Complex calculate(Complex actual) {
        return a.multiply(actual).add(b);
    }

    public static Function from(Complex a, Complex b) {
        return new Linear(a, b);
    }

    public static Function withB(Complex b) {
        return new Linear(Complex.ONE, b);
    }

    @Override
    public String toString() {
        return "(" + a + "*x + " + b + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Linear)) {
            return false;
        }
        Linear other = (Linear) obj;
        return a.equals(other.a) && b.equals(other.b);
    }

    @Override
    public int hashCode() {
        return a.hashCode() + b.hashCode();
    }

}
