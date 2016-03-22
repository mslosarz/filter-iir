package pl.edu.pk.aipsc.math;

import org.apache.commons.math3.complex.Complex;

import java.awt.*;

import static pl.edu.pk.aipsc.math.ComplexCalculator.resolveAnalog;
import static pl.edu.pk.aipsc.math.ComplexCalculator.resolveDigital;

public class ComplexPoint {
    private final Complex complex;
    private final Type type;

    private ComplexPoint(Complex complex, Type type) {
        this.complex = complex;
        this.type = type;
    }

    public static ComplexPoint createZero(Complex complex) {
        return new ComplexPoint(complex, Type.ZERO);
    }

    public static ComplexPoint createCustom(Complex complex, Type type) {
        return new ComplexPoint(complex, type);
    }

    public static ComplexPoint createPole(Complex complex) {
        return new ComplexPoint(complex, Type.POLE);
    }

    public Type getType() {
        return type;
    }

    public Complex getComplex() {
        return complex;
    }

    public void drawOn(Rectangle drawingArea, Graphics graphics) {
        String sign = type.getSignToDraw();
        Point point = resolveDigital(drawingArea, complex);
        graphics.drawString(sign, point.x - 4, point.y + 4);
    }

    public void drawOn(Rectangle drawingArea, Graphics graphics, int max) {
        String sign = type.getSignToDraw();
        Point point = resolveAnalog(drawingArea, complex, max);
        graphics.drawString(sign, point.x - 4, point.y + 4);
    }

    public static enum Type {
        ZERO("O"), POLE("X");

        private Type(String signToDraw) {
            this.signToDraw = signToDraw;
        }

        private String signToDraw;

        public String getSignToDraw() {
            return signToDraw;
        }
    }

    @Override
    public String toString() {
        return "ComplexPoint [complex=" + complex + ", type=" + type + "]";
    }

}
