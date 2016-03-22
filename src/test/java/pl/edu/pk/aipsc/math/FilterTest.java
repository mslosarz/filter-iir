package pl.edu.pk.aipsc.math;

import org.apache.commons.math3.complex.Complex;
import org.math.plot.Plot2DPanel;
import pl.edu.pk.aipsc.common.Observer;
import pl.edu.pk.aipsc.panel.DigitalPanel;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.log;
import static org.apache.commons.math3.complex.Complex.ONE;
import static org.apache.commons.math3.complex.Complex.ZERO;

public class FilterTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame panel = new JFrame();
                    panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    panel.setSize(400, 400);
                    panel.getContentPane()
                            .setLayout(new GridLayout(1, 0, 0, 0));

                    final Plot2DPanel plot2DPanel = new Plot2DPanel();
                    panel.getContentPane().add(plot2DPanel);

                    DigitalPanel complexPanel = new DigitalPanel();
                    panel.getContentPane().add(complexPanel);
                    panel.setVisible(true);

                    final DigitalFilter f = new DigitalFilter(complexPanel);
                    final Complex[] function = new Complex[1024];
                    final int length = function.length;
                    for (int i = 0; i < length; i++) {
                        function[i] = ZERO;
                    }
                    function[0] = ONE;
                    f.addObserver(new Observer() {
                        @Override
                        public void update() {
                            Complex[] result = f.fft(function);
                            double[] absResult = new double[length];
                            for (int i = 0; i < length; i++) {
                                absResult[i] = 20 * log(result[i].abs());
                            }
                            plot2DPanel.removeAllPlots();
                            plot2DPanel.addLinePlot("a", absResult);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
