package pl.edu.pk.aipsc.panel;

import pl.edu.pk.aipsc.math.ComplexPoint.Type;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.event.InputEvent.BUTTON1_DOWN_MASK;
import static java.awt.event.InputEvent.BUTTON3_DOWN_MASK;
import static pl.edu.pk.aipsc.math.ComplexPoint.Type.POLE;
import static pl.edu.pk.aipsc.math.ComplexPoint.Type.ZERO;

class MouseBehavior extends MouseAdapter {

    private final ComplexPanel panel;
    private MouseEvent last;
    private Type type;

    public MouseBehavior(ComplexPanel panel) {
        this.panel = panel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        last = e;
        type = null;
        extractType(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDragged(e);
    }

    private Type extractType(MouseEvent e) {
        if (type == null) {
            if (e.getModifiersEx() == BUTTON1_DOWN_MASK) {
                type = POLE;
            }
            if (e.getModifiersEx() == BUTTON3_DOWN_MASK) {
                type = ZERO;
            }
        }
        return type;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (last != null) {
            panel.removePoint(last.getPoint());
            panel.addPoint(e.getPoint(), extractType(e));
        }
        last = e;
    }
}