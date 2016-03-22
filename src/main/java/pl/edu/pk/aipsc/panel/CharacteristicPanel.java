package pl.edu.pk.aipsc.panel;

import org.apache.commons.math3.complex.Complex;
import org.math.plot.Plot2DPanel;
import pl.edu.pk.aipsc.common.Observer;
import pl.edu.pk.aipsc.math.Filter;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.log;
import static org.apache.commons.math3.complex.Complex.ONE;
import static org.apache.commons.math3.complex.Complex.ZERO;

@SuppressWarnings("serial")
public class CharacteristicPanel extends JPanel implements Observer {

    private Filter filter;

    private Complex[] signal;

    protected Plot2DPanel panel = new Plot2DPanel();

    private CharacteristicPanel() {
        setLayout(new GridLayout(0, 1, 0, 0));
        panel.setAxisLabel(0, "frequency (Hz)");
        panel.setAxisLabel(1, "gain (dB) (20*log(|H|)");
        panel.setName("Frequency Characteristics");
        add(panel);
    }

    public CharacteristicPanel(Filter filter, Complex[] signal) {
        this();
        this.filter = filter;
        filter.addObserver(this);
        this.signal = signal;
    }

    public CharacteristicPanel(Filter filter) {
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
        double[] amplification = new double[filterResult.length];

        for (int i = 0; i < amplification.length; i++) {
            amplification[i] = 20 * log(filterResult[i].abs());
        }

        double[] freq = new double[amplification.length / 2];
        for (int i = 1; i < freq.length; i++) {
            freq[i] = i;
        }

        panel.removeAllPlots();
        panel.addLinePlot("Characteristics", freq, amplification);

    }

}
