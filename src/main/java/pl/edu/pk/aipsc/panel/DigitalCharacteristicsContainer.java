package pl.edu.pk.aipsc.panel;

import pl.edu.pk.aipsc.math.DigitalFilter;
import pl.edu.pk.aipsc.math.DigitalTestFunction;
import pl.edu.pk.aipsc.math.Filter;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class DigitalCharacteristicsContainer extends JPanel {

    private DigitalPanel panel;

    private Filter filter;

    private CharacteristicPanel characteristicPanel;

    private AnglePanel anglePanel;

    private FunctionPanel function;

    public DigitalCharacteristicsContainer() {
        setLayout(new GridLayout(2, 1, 0, 0));
        panel = new DigitalPanel();
        filter = new DigitalFilter(panel);
        characteristicPanel = new CharacteristicPanel(filter);
        anglePanel = new AnglePanel(filter);
        function = new FunctionPanel(filter, new DigitalTestFunction());

        add(panel);
        add(anglePanel);
        add(characteristicPanel);
        add(function);
    }

    public DigitalPanel getPanel() {
        return panel;
    }

    public Filter getFilter() {
        return filter;
    }

    public CharacteristicPanel getCharacteristicPanel() {
        return characteristicPanel;
    }

    public AnglePanel getAnglePanel() {
        return anglePanel;
    }

    public FunctionPanel getFunction() {
        return function;
    }


}
