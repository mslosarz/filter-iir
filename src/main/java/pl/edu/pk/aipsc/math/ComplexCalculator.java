package pl.edu.pk.aipsc.math;

import org.apache.commons.math3.complex.Complex;

import java.awt.*;

public class ComplexCalculator {

    private ComplexCalculator() {
    }

    /**
     * Funkcja przelicza pozycje w prostokącie (stanowiącym koło jednostkowe
     * liczby zespolonej) na odpowiednią wartość funkcji zespolonej
     */
    public static Complex resolveDigital(Rectangle area, Point point) {
        double width = (area.getMaxX() - area.getMinX()) / 2;
        double height = (area.getMaxY() - area.getMinY()) / 2;
        return new Complex((point.getX() - area.getMinX() - width) / width, (area.getCenterY() - point.getY()) / height);
    }

    /**
     * Funkcja przelicza liczbę zespoloną na pozycje w prostokącie
     */
    public static Point resolveDigital(Rectangle area, Complex z) {
        double width = (area.getMaxX() - area.getMinX()) / 2;
        double height = (area.getMaxY() - area.getMinY()) / 2;
        ;
        return new Point((int) (area.getCenterX() + z.getReal() * width), (int) (area.getCenterY() - z.getImaginary() * height));
    }

    /**
     * Funkcja przelicza liczbę zespoloną na pozycje w prostokącie
     */
    public static Point resolveAnalog(Rectangle area, Complex z, int maxValue) {
        double width = (area.getMaxX() - area.getMinX()) / 2;
        double height = (area.getMaxY() - area.getMinY()) / 2;
        return new Point((int) (area.getCenterX() + (z.getReal() / maxValue) * width), (int) (area.getCenterY() - (z.getImaginary() / maxValue) * height));
    }

    /**
     * Funkcja przelicza pozycje w prostokącie (stanowiącym koło jednostkowe
     * liczby zespolonej) na odpowiednią wartość funkcji zespolonej
     */
    public static Complex resolveAnalog(Rectangle area, Point point, int maxValue) {
        double width = (area.getMaxX() - area.getMinX()) / 2;
        double height = (area.getMaxY() - area.getMinY()) / 2;
        return new Complex((point.getX() - area.getMinX() - width) * maxValue / width, (area.getCenterY() - point.getY()) * maxValue / height);
    }
}
