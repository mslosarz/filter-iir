package pl.edu.pk.aipsc.panel;

import org.apache.commons.math3.complex.Complex;
import org.math.plot.Plot2DPanel;
import pl.edu.pk.aipsc.common.Observer;
import pl.edu.pk.aipsc.math.Filter;

import javax.swing.*;
import java.awt.*;

import static org.apache.commons.math3.complex.Complex.ONE;
import static org.apache.commons.math3.complex.Complex.ZERO;

@SuppressWarnings("serial")
public class AnglePanel extends JPanel implements Observer {

    private Filter filter;

    private Complex[] signal;

    private Plot2DPanel panel = new Plot2DPanel();

    private AnglePanel() {
        setLayout(new GridLayout(0, 1, 0, 0));
        panel.setAxisLabel(0, "frequency (Hz)");
        panel.setAxisLabel(1, "phase");
        panel.setName("Angle Characteristics");
        add(panel);
    }

    public AnglePanel(Filter filter, Complex[] signal) {
        this();
        this.filter = filter;
        filter.addObserver(this);
        this.signal = signal;
    }

    public AnglePanel(Filter filter) {
        this();
        this.filter = filter;
        filter.addObserver(this);
        fillSignalByDiracDelta();
    }

    private void fillSignalByDiracDelta() {
        signal = new Complex[1024];
        signal[0] = ONE;
        for (int i = 1; i < signal.length; i++) {
            signal[i] = ZERO;
        }

    }

    @Override
    public void update() {
        Complex[] filterResult = filter.fft(signal);
        double[] phase = new double[filterResult.length];

        for (int i = 0; i < phase.length; i++) {
            phase[i] = filterResult[i].getArgument();
        }

        double[] freq = new double[phase.length / 2];
        for (int i = 1; i < freq.length; i++) {
            freq[i] = i;
        }

        panel.removeAllPlots();
        panel.addLinePlot("Characteristics", freq, phase);

    }

}
