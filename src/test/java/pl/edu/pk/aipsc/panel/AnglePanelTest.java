package pl.edu.pk.aipsc.panel;

import pl.edu.pk.aipsc.math.DigitalFilter;
import pl.edu.pk.aipsc.math.Filter;

import javax.swing.*;
import java.awt.*;

public class AnglePanelTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(400, 400);
                    frame.getContentPane()
                            .setLayout(new GridLayout(1, 0, 0, 0));

                    DigitalPanel panel = new DigitalPanel();
                    frame.add(panel);

                    Filter filter = new DigitalFilter(panel);

                    AnglePanel anglePanel = new AnglePanel(filter);
                    frame.add(anglePanel);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
