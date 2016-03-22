package pl.edu.pk.aipsc.panel;

import org.junit.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static pl.edu.pk.aipsc.math.ComplexPoint.Type.POLE;

/**
 * User: mslosarz
 */
public class MouseBehaviorTest {

    @Test
    public void shouldAddPointOnReleaseClick() {
        //given
        DigitalPanel panel = mock(DigitalPanel.class);
        MouseBehavior behavior = new MouseBehavior(panel);

        Point point = mock(Point.class);
        MouseEvent event = mockMouseEvent(point, InputEvent.BUTTON1_MASK);

        //when
        behavior.mousePressed(event);
        behavior.mouseReleased(event);

        //then
        verify(panel, atMost(2)).addPoint(point, POLE);
        verify(panel, atMost(1)).removePoint(point);
    }

    @Test
    public void shouldCheckIsDrawingLeaveRealAndOneConjugatePoint() {
        //given
        ComplexPanel panel = stubPanel();
        MouseBehavior behavior = new MouseBehavior(panel);

        Point point = mockPoint(100, 100);
        MouseEvent press = mockMouseEvent(point, InputEvent.BUTTON1_DOWN_MASK);

        point = mockPoint(121, 121);
        MouseEvent release = mockMouseEvent(point, InputEvent.BUTTON1_MASK);

        MouseEvent dragging;

        //when
        behavior.mousePressed(press);
        for (int i = 0; i < 20; i++) {
            dragging = mockMouseEvent(mockPoint(100 + i, 100 + i), InputEvent.BUTTON1_DOWN_MASK);
            behavior.mouseDragged(dragging);
        }


        //then
        assertThat(panel.complexSize(), is(2));
    }

    private MouseEvent mockMouseEvent(Point point, int buttonModifier) {
        MouseEvent event = mock(MouseEvent.class);
        when(event.getModifiersEx()).thenReturn(buttonModifier);
        when(event.getPoint()).thenReturn(point);
        return event;
    }

    private ComplexPanel stubPanel() {
        return new DigitalPanel() {
            @Override
            public Rectangle getVisibleRect() {
                return new Rectangle(400, 400);
            }
        };
    }

    private Point mockPoint(int x, int y) {
        Point point = mock(Point.class);
        when(point.getX()).thenReturn(Double.valueOf(x));
        when(point.getY()).thenReturn(Double.valueOf(y));
        return point;
    }
}
