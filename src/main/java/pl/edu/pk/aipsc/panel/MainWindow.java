package pl.edu.pk.aipsc.panel;

import org.apache.commons.math3.complex.Complex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static pl.edu.pk.aipsc.math.ComplexPoint.createPole;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

    private final DigitalCharacteristicsContainer digitalCharacteristicsContainer;
    private final AnalogCharacteristicContainer analogCharacteristicContainer;

    public MainWindow() {
        getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        digitalCharacteristicsContainer = new DigitalCharacteristicsContainer();
        analogCharacteristicContainer = new AnalogCharacteristicContainer();

        JTabbedPane filters = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(filters);

        createAnalogPanel(filters);
        createDigitalPanel(filters);
    }

    private void createDigitalPanel(JTabbedPane filters) {
        JPanel digitalFilter = new JPanel();
        filters.addTab("Digital filter", null, digitalFilter, null);
        digitalFilter.setLayout(new BorderLayout(0, 0));

        createDigitalToolBar(digitalFilter);

        digitalFilter.add(digitalCharacteristicsContainer, BorderLayout.CENTER);
    }

    private void createDigitalToolBar(JPanel digitalFilter) {
        JToolBar digitalToolBar = new JToolBar();
        digitalFilter.add(digitalToolBar, BorderLayout.NORTH);

        JButton clearFilter = new JButton("Clear");
        clearFilter.setToolTipText("Clear all added zeros and poles");
        clearFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                digitalCharacteristicsContainer.getPanel().clear();
            }
        });

        JButton chebyshev2 = new JButton("LP Ch 2");
        chebyshev2.setToolTipText("Low-pass Chebyshev filter 2");
        chebyshev2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DigitalPanel panel = digitalCharacteristicsContainer.getPanel();
                Complex p1 = new Complex(0.43, 0.52);
                panel.addComplex(createPole(p1));
                panel.addComplex(createPole(p1.conjugate()));
            }
        });


        JButton chebyshev4 = new JButton("LP Ch 4");
        chebyshev4.setToolTipText("Low-pass Chebyshev filter 4");
        chebyshev4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DigitalPanel panel = digitalCharacteristicsContainer.getPanel();
                Complex p1 = new Complex(0.31, 0.77);
                panel.addComplex(createPole(p1));
                panel.addComplex(createPole(p1.conjugate()));
                Complex p2 = new Complex(0.49, 0.24);
                panel.addComplex(createPole(p2));
                panel.addComplex(createPole(p2.conjugate()));
            }
        });

        JButton chebyshev6 = new JButton("LP Ch 6");
        chebyshev6.setToolTipText("Low-pass Chebyshev filter 6");
        chebyshev6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DigitalPanel panel = digitalCharacteristicsContainer.getPanel();
                Complex p1 = new Complex(0.23, 0.88);
                panel.addComplex(createPole(p1));
                panel.addComplex(createPole(p1.conjugate()));
                Complex p2 = new Complex(0.47, 0.34);
                panel.addComplex(createPole(p2));
                panel.addComplex(createPole(p2.conjugate()));
                Complex p3 = new Complex(0.49, 0.14);
                panel.addComplex(createPole(p3));
                panel.addComplex(createPole(p3.conjugate()));
            }
        });

        JButton butterworth2 = new JButton("LP Bt 2");
        butterworth2.setToolTipText("Low-pass Butterworth filter 2");
        butterworth2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DigitalPanel panel = digitalCharacteristicsContainer.getPanel();
                Complex p1 = new Complex(0.69, 0.69);
                panel.addComplex(createPole(p1));
                panel.addComplex(createPole(p1.conjugate()));
            }
        });


        JButton butterworth4 = new JButton("LP Bt 4");
        butterworth4.setToolTipText("Low-pass Butterworth filter 4");
        butterworth4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DigitalPanel panel = digitalCharacteristicsContainer.getPanel();
                Complex p1 = new Complex(0.41, 0.88);
                panel.addComplex(createPole(p1));
                panel.addComplex(createPole(p1.conjugate()));
                Complex p2 = new Complex(0.88, 0.41);
                panel.addComplex(createPole(p2));
                panel.addComplex(createPole(p2.conjugate()));
            }
        });

        JButton butterworth6 = new JButton("LP Bt 6");
        butterworth6.setToolTipText("Low-pass Butterworth filter 6");
        butterworth6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DigitalPanel panel = digitalCharacteristicsContainer.getPanel();
                Complex p1 = new Complex(0.69, 0.69);
                panel.addComplex(createPole(p1));
                panel.addComplex(createPole(p1.conjugate()));
                Complex p2 = new Complex(0.32, 0.93);
                panel.addComplex(createPole(p2));
                panel.addComplex(createPole(p2.conjugate()));
                Complex p3 = new Complex(0.92, 0.32);
                panel.addComplex(createPole(p3));
                panel.addComplex(createPole(p3.conjugate()));
            }
        });

        digitalToolBar.add(clearFilter);
        digitalToolBar.add(chebyshev2);
        digitalToolBar.add(chebyshev4);
        digitalToolBar.add(chebyshev6);
        digitalToolBar.add(butterworth2);
        digitalToolBar.add(butterworth4);
        digitalToolBar.add(butterworth6);
    }

    private void createAnalogPanel(JTabbedPane filters) {
        JPanel analogFilter = new JPanel();
        filters.addTab("Analog filter", null, analogFilter, null);
        analogFilter.setLayout(new BorderLayout(0, 0));

        createAnalogToolBar(analogFilter);

        analogFilter.add(analogCharacteristicContainer, BorderLayout.CENTER);
    }

    private void createAnalogToolBar(JPanel digitalFilter) {
        JToolBar analogToolBar = new JToolBar();
        digitalFilter.add(analogToolBar, BorderLayout.NORTH);

        JButton clearFilter = new JButton("Clear");
        clearFilter.setToolTipText("Clear all added zeros and poles");
        clearFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                analogCharacteristicContainer.getPanel().clear();
            }
        });

        JButton chebyshev2 = new JButton("LP Ch 2");
        chebyshev2.setToolTipText("Low-pass Chebyshev filter 2");
        chebyshev2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComplexPanel panel = analogCharacteristicContainer.getPanel();
                Complex p1 = new Complex(0.43, 0.52);
                panel.addComplex(createPole(toAnalog(p1)));
                panel.addComplex(createPole(toAnalog(p1).conjugate()));
            }
        });


        JButton chebyshev4 = new JButton("LP Ch 4");
        chebyshev4.setToolTipText("Low-pass Chebyshev filter 4");
        chebyshev4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComplexPanel panel = analogCharacteristicContainer.getPanel();
                Complex p1 = new Complex(0.31, 0.77);
                panel.addComplex(createPole(toAnalog(p1)));
                panel.addComplex(createPole(toAnalog(p1).conjugate()));
                Complex p2 = new Complex(0.49, 0.24);
                panel.addComplex(createPole(toAnalog(p2)));
                panel.addComplex(createPole(toAnalog(p2).conjugate()));
            }
        });

        JButton chebyshev6 = new JButton("LP Ch 6");
        chebyshev6.setToolTipText("Low-pass Chebyshev filter 6");
        chebyshev6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComplexPanel panel = analogCharacteristicContainer.getPanel();
                Complex p1 = new Complex(0.23, 0.88);
                panel.addComplex(createPole(toAnalog(p1)));
                panel.addComplex(createPole(toAnalog(p1).conjugate()));
                Complex p2 = new Complex(0.47, 0.34);
                panel.addComplex(createPole(toAnalog(p2)));
                panel.addComplex(createPole(toAnalog(p2).conjugate()));
                Complex p3 = new Complex(0.49, 0.14);
                panel.addComplex(createPole(toAnalog(p3)));
                panel.addComplex(createPole(toAnalog(p3).conjugate()));
            }
        });

        JButton butterworth2 = new JButton("LP Bt 2");
        butterworth2.setToolTipText("Low-pass Butterworth filter 2");
        butterworth2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComplexPanel panel = analogCharacteristicContainer.getPanel();
                Complex p1 = new Complex(0.69, 0.69);
                panel.addComplex(createPole(toAnalog(p1)));
                panel.addComplex(createPole(toAnalog(p1).conjugate()));
            }
        });


        JButton butterworth4 = new JButton("LP Bt 4");
        butterworth4.setToolTipText("Low-pass Butterworth filter 4");
        butterworth4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComplexPanel panel = analogCharacteristicContainer.getPanel();
                Complex p1 = new Complex(0.41, 0.88);
                panel.addComplex(createPole(toAnalog(p1)));
                panel.addComplex(createPole(toAnalog(p1).conjugate()));
                Complex p2 = new Complex(0.88, 0.41);
                panel.addComplex(createPole(toAnalog(p2)));
                panel.addComplex(createPole(toAnalog(p2).conjugate()));
            }
        });

        JButton butterworth6 = new JButton("LP Bt 6");
        butterworth6.setToolTipText("Low-pass Butterworth filter 6");
        butterworth6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComplexPanel panel = analogCharacteristicContainer.getPanel();
                Complex p1 = new Complex(0.69, 0.69);
                panel.addComplex(createPole(toAnalog(p1)));
                panel.addComplex(createPole(toAnalog(p1).conjugate()));
                Complex p2 = new Complex(0.32, 0.93);
                panel.addComplex(createPole(toAnalog(p2)));
                panel.addComplex(createPole(toAnalog(p2).conjugate()));
                Complex p3 = new Complex(0.92, 0.32);
                panel.addComplex(createPole(toAnalog(p3)));
                panel.addComplex(createPole(toAnalog(p3).conjugate()));
            }
        });

        analogToolBar.add(clearFilter);
        analogToolBar.add(chebyshev2);
        analogToolBar.add(chebyshev4);
        analogToolBar.add(chebyshev6);
        analogToolBar.add(butterworth2);
        analogToolBar.add(butterworth4);
        analogToolBar.add(butterworth6);
    }

    public Complex toAnalog(Complex z) {
        double T = analogCharacteristicContainer.getFilter().getT();
        Complex s = z.subtract(Complex.ONE).divide(z.add(Complex.ONE)).multiply(2 / T);
        return s;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainWindow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
