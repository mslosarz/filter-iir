package pl.edu.pk.aipsc.panel;

import org.apache.commons.math3.complex.Complex;
import pl.edu.pk.aipsc.common.Observable;
import pl.edu.pk.aipsc.common.Observer;
import pl.edu.pk.aipsc.math.ComplexPoint;
import pl.edu.pk.aipsc.math.ComplexPoint.Type;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public abstract class ComplexPanel extends JPanel implements Observable {

    protected List<Observer> observers = new LinkedList<>();

    protected List<ComplexPoint> points = new LinkedList<>();
    final int border = 15;

    public abstract void addPoint(Point point, Type type);

    public abstract void removePoint(Point point);

    public abstract void removeComplex(Complex center);

    public List<ComplexPoint> getPoints() {
        return points;
    }

    public void addComplex(ComplexPoint point) {
        points.add(point);
        updateObservers();
        repaint();
    }

    public int complexSize() {
        return points.size();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void updateObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void clear() {
        points.clear();
        repaint();
        updateObservers();
    }

}