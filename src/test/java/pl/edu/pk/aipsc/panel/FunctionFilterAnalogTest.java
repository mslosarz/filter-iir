package pl.edu.pk.aipsc.panel;

import pl.edu.pk.aipsc.math.AnalogFilter;
import pl.edu.pk.aipsc.math.Filter;

import javax.swing.*;
import java.awt.*;

public class FunctionFilterAnalogTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(400, 400);
                    frame.getContentPane()
                            .setLayout(new GridLayout(1, 0, 0, 0));

                    AnalogPanel panel = new AnalogPanel();
                    frame.add(panel);

                    Filter filter = new AnalogFilter(panel);

                    CharacteristicPanel characteristicPanel = new CharacteristicPanel(filter);
                    frame.add(characteristicPanel);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
