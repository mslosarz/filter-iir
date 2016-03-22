package pl.edu.pk.aipsc.panel;

import pl.edu.pk.aipsc.math.AnalogFilter;
import pl.edu.pk.aipsc.math.AnalogTestFunction;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class AnalogCharacteristicContainer extends JPanel {

    private AnalogPanel panel;

    private AnalogFilter filter;

    private CharacteristicPanel characteristicPanel;

    private AnglePanel anglePanel;

    private FunctionPanel function;

    public AnalogCharacteristicContainer() {
        setLayout(new GridLayout(2, 1, 0, 0));
        panel = new AnalogPanel();
        filter = new AnalogFilter(panel);
        characteristicPanel = new CharacteristicPanel(filter);
        anglePanel = new AnglePanel(filter);
        function = new FunctionPanel(filter, new AnalogTestFunction());

        add(panel);
        add(anglePanel);
        add(characteristicPanel);
        add(function);
    }

    public AnalogPanel getPanel() {
        return panel;
    }

    public AnalogFilter getFilter() {
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
