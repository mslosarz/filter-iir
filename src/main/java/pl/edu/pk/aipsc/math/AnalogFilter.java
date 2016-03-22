package pl.edu.pk.aipsc.math;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.util.ArithmeticUtils;
import pl.edu.pk.aipsc.common.Observer;
import pl.edu.pk.aipsc.panel.ComplexPanel;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.*;
import static org.apache.commons.math3.transform.DftNormalization.STANDARD;
import static org.apache.commons.math3.transform.TransformType.FORWARD;
import static org.apache.commons.math3.transform.TransformType.INVERSE;
import static pl.edu.pk.aipsc.math.ComplexPoint.Type.POLE;
import static pl.edu.pk.aipsc.math.ComplexPoint.Type.ZERO;
import static pl.edu.pk.aipsc.math.Polynomial.polynomial;

public class AnalogFilter implements Filter {

    private ComplexPanel panel;
    private FastFourierTransformer transformer = new FastFourierTransformer(
            STANDARD);
    private Polynomial polynominal = Polynomial.polynomial();
    private List<Observer> observers = new LinkedList<>();
    private double T = 5e-3;

    public AnalogFilter(ComplexPanel panel) {
        this.panel = panel;
        panel.addObserver(this);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void updateObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    @Override
    public void update() {
        // pobierz bieguny i zera
        List<ComplexPoint> points = panel.getPoints();

        // stwórz nowy wielomian
        polynominal = polynomial();
        for (ComplexPoint point : points) {
            // z = (1 + sT/2)/(1-sT/2)
            Complex sT = point.getComplex().multiply(T).divide(2);

            Complex z = Complex.ONE.add(sT).divide(Complex.ONE.subtract(sT));

            //  jeżeli biegun to podziel przez wartość
            if (point.getType() == POLE) {
                polynominal.div(Linear.withB(z.negate()));

                // jeżeli zero to pomnóż
            } else if (point.getType() == ZERO) {
                polynominal.mul(Linear.withB(z.negate()));
            }
        }

        // poinformuj reszte komponentów o zmianie
        updateObservers();
    }

    @Override
    public Complex[] fft(Complex[] args) {
        if (ArithmeticUtils.isPowerOfTwo(args.length) == false) {
            throw new IllegalArgumentException(
                    "Length of args must be power of 2 but is " + args.length);
        }

        Complex[] fft = transformer.transform(args, FORWARD);

        for (int i = 0; i < fft.length; i++) { // przebiegamy po pulsacji
            double omega = 2 * PI * i / (fft.length - 1);
            fft[i] = fft[i].multiply(polynominal.calculate(new Complex(
                    cos(omega), sin(omega))));
        }
        return fft;
    }

    @Override
    public Complex[] filter(Complex[] args) {
        return transformer.transform(fft(args), INVERSE);
    }

    public double getT() {
        return T;
    }

}
