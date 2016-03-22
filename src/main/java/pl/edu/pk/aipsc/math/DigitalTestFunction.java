package pl.edu.pk.aipsc.math;

import org.apache.commons.math3.complex.Complex;

import static java.lang.Math.PI;
import static java.lang.Math.sin;

public class DigitalTestFunction implements Function {

    @Override
    public Complex calculate(Complex actual) {
        return new Complex(10 * sin(10 * 2 * PI * actual.getReal()) + 4 * sin(100 * 2 * PI * actual.getReal()));
    }

    @Override
    public String toString() {
        return "10*sin(10*2PI*t)+4*sin(100*2PI*t)";
    }

}
