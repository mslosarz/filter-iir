package pl.edu.pk.aipsc.panel;

import pl.edu.pk.aipsc.math.DigitalFilter;
import pl.edu.pk.aipsc.math.DigitalTestFunction;
import pl.edu.pk.aipsc.math.Filter;

import javax.swing.*;
import java.awt.*;

public class AmplificationAndAngleTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(400, 400);
                    frame.getContentPane()
                            .setLayout(new GridLayout(2, 1, 0, 0));

                    DigitalPanel panel = new DigitalPanel();
                    frame.add(panel);

                    Filter filter = new DigitalFilter(panel);

                    CharacteristicPanel characteristicPanel = new CharacteristicPanel(filter);
                    AnglePanel anglePanel = new AnglePanel(filter);
                    FunctionPanel function = new FunctionPanel(filter, new DigitalTestFunction());
                    frame.add(anglePanel);
                    frame.add(characteristicPanel);
                    frame.add(function);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
