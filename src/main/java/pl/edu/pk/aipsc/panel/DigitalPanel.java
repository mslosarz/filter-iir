package pl.edu.pk.aipsc.panel;

import org.apache.commons.math3.complex.Complex;
import pl.edu.pk.aipsc.math.ComplexPoint;
import pl.edu.pk.aipsc.math.ComplexPoint.Type;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import static pl.edu.pk.aipsc.math.ComplexCalculator.resolveDigital;
import static pl.edu.pk.aipsc.math.ComplexPoint.createCustom;

@SuppressWarnings("serial")
public class DigitalPanel extends ComplexPanel {

    public DigitalPanel() {
        setBackground(Color.WHITE);
        MouseBehavior mouse = new MouseBehavior(this);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.setColor(Color.BLACK);
        drawUnitCircleOn(graphics);
        drawPointsOn(graphics);
    }

    private void drawPointsOn(Graphics graphics) {
        for (ComplexPoint point : points) {
            point.drawOn(getDrawingArea(getVisibleRect()), graphics);
        }
    }

    private Rectangle getDrawingArea(Rectangle rectangle) {
        return new Rectangle(
                border,
                border,
                (int) (rectangle.getWidth() - 2 * border),
                (int) (rectangle.getHeight() - 2 * border)
        );
    }

    private void drawUnitCircleOn(Graphics g) {
        int width = getWidth() - 2 * border;
        int height = getHeight() - 2 * border;
        g.drawOval(border, border, width, height);

        int yPos = border + height / 2;
        g.drawLine(0, yPos, getWidth(), yPos);
        g.drawString("Re", getWidth() - border + 1, yPos);

        int xPos = border + (width) / 2;
        g.drawLine(xPos, 0, xPos, getHeight());
        g.drawString("Im", xPos + 1, border - 1);
    }

    @Override
    public void addPoint(Point point, Type type) {
        Rectangle area = getDrawingArea(getVisibleRect());
        Complex complex = resolveDigital(area, point);
        addComplex(createCustom(complex, type));
        addComplex(createCustom(complex.conjugate(), type));
    }

    @Override
    public void removePoint(Point point) {
        Rectangle area = getDrawingArea(getVisibleRect());
        Complex complex = resolveDigital(area, point);
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
        Point centralPoint = resolveDigital(area, central);
        Point actual = resolveDigital(area, complex);
        double radius = 4; // odległość w px
        return centralPoint.distance(actual) < radius;
    }

}
