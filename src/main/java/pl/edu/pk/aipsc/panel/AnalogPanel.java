package pl.edu.pk.aipsc.panel;

import org.apache.commons.math3.complex.Complex;
import pl.edu.pk.aipsc.math.ComplexPoint;
import pl.edu.pk.aipsc.math.ComplexPoint.Type;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import static pl.edu.pk.aipsc.math.ComplexCalculator.resolveAnalog;
import static pl.edu.pk.aipsc.math.ComplexPoint.createCustom;

@SuppressWarnings("serial")
public class AnalogPanel extends ComplexPanel {

    final int max = 2200;

    public AnalogPanel() {
        setBackground(Color.WHITE);
        MouseBehavior mouse = new MouseBehavior(this);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.setColor(Color.BLACK);
        drawCrissCrossOn(graphics);
        drawPointsOn(graphics);
    }

    private void drawPointsOn(Graphics graphics) {
        for (ComplexPoint point : points) {
            point.drawOn(getVisibleRect(), graphics, max);
        }
    }

    private void drawCrissCrossOn(Graphics g) {
        int width = getWidth() - 2 * border;
        int height = getHeight() - 2 * border;

        int yPos = border + height / 2;
        g.drawLine(0, yPos, getWidth(), yPos);
        g.drawString("Re", getWidth() - border + 1, yPos);
        g.drawString("" + max, getWidth() - 5 * 8, yPos + 12);
        g.drawString("-" + max, 0, yPos + 12);

        int xPos = border + width / 2;
        g.drawLine(xPos, 0, xPos, getHeight());
        g.drawString("Im", xPos + 1, border - 1);
        g.drawString("" + max, xPos - 5 * 8, border);
        g.drawString("-" + max, xPos - 5 * 9, getHeight());
    }

    @Override
    public void addPoint(Point point, Type type) {
        Rectangle area = getVisibleRect();
        Complex complex = resolveAnalog(area, point, max);
        addComplex(createCustom(complex, type));
        addComplex(createCustom(complex.conjugate(), type));
    }

    @Override
    public void removePoint(Point point) {
        Rectangle area = getVisibleRect();
        Complex complex = resolveAnalog(area, point, max);
        removeComplex(complex);
        removeComplex(complex.conjugate());
    }

    @Override
    public void removeComplex(Complex center) {
        List<ComplexPoint> toRemove = new LinkedList<>();
        for (ComplexPoint point : points) {
            if (isCloseEnough(center, point.getComplex())) {
                toRemove.add(point);
            }
        }
        points.removeAll(toRemove);
        updateObservers();
        repaint();
    }

    private boolean isCloseEnough(Complex central, Complex complex) {
        Rectangle area = getVisibleRect();
        Point centralPoint = resolveAnalog(area, central, max);
        Point actual = resolveAnalog(area, complex, max);
        double radius = 4; // odległość w px
        return centralPoint.distance(actual) < radius;
    }

}
