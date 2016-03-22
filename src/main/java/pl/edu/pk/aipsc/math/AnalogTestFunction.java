package pl.edu.pk.aipsc.math;

import org.apache.commons.math3.complex.Complex;

import static java.lang.Math.PI;
import static java.lang.Math.sin;

public class AnalogTestFunction implements Function {

    @Override
    public Complex calculate(Complex actual) {
        return new Complex(20 * sin(20 * 2 * PI * actual.getReal()) + 2 * sin(100 * 2 * PI * actual.getReal()));
    }

    @Override
    public String toString() {
        return "20*sin(20*2PI*t)+2*sin(100*2PI*t)";
    }

}
