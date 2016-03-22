package pl.edu.pk.aipsc.panel;

import org.apache.commons.math3.complex.Complex;
import org.math.plot.Plot2DPanel;
import pl.edu.pk.aipsc.common.Observer;
import pl.edu.pk.aipsc.math.Filter;
import pl.edu.pk.aipsc.math.Function;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class FunctionPanel extends JPanel implements Observer {

    private Filter filter;

    private Function function;

    private Complex[] signal;

    private Plot2DPanel panel = new Plot2DPanel();

    private FunctionPanel(Function function) {
        setLayout(new GridLayout(0, 1, 0, 0));
        this.function = function;
        panel.setAxisLabel(0, "time (s)");
        panel.setAxisLabel(1, "      " + function);
        panel.setName("Function ");
        add(panel);
    }

    public FunctionPanel(Filter filter, Complex[] signal, Function function) {
        this(function);
        this.filter = filter;
        filter.addObserver(this);
        this.signal = signal;
    }

    public FunctionPanel(Filter filter, Function function) {
        this(function);
        this.filter = filter;
        filter.addObserver(this);
        fillSignalBySomeFunction();
    }

    private void fillSignalBySomeFunction() {
        signal = new Complex[1024];
        for (int i = 0; i < signal.length; i++) {
            double step = ((double) i) / signal.length;
            signal[i] = function.calculate(new Complex(step));
        }

    }

    @Override
    public void update() {
        Complex[] function = filter.filter(signal);

        double[] time = new double[function.length / 2];
        for (int i = 0; i < time.length; i++) {
            time[i] = 0.3 * i;
        }

        double[] realFunction = new double[function.length];
        for (int i = 0; i < realFunction.length; i++) {
            realFunction[i] = function[i].getReal();
        }

        panel.removeAllPlots();
        panel.addLinePlot("function", time, realFunction);

    }

}
