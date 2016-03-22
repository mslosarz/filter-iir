package pl.edu.pk.aipsc.panel;

import org.apache.commons.math3.complex.Complex;
import org.junit.Test;
import pl.edu.pk.aipsc.common.Observer;
import pl.edu.pk.aipsc.math.ComplexPoint;
import pl.edu.pk.aipsc.math.ComplexPoint.Type;

import javax.swing.*;
import java.awt.*;

import static org.apache.commons.math3.complex.Complex.ONE;
import static org.apache.commons.math3.complex.Complex.ZERO;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static pl.edu.pk.aipsc.math.ComplexPoint.createPole;
import static pl.edu.pk.aipsc.math.ComplexPoint.createZero;


public class ComplexPanelTest {

    @Test
    public void shouldNotifyObserversAboutChangeValue() {
        //given
        Observer observer = mock(Observer.class);
        DigitalPanel panel = new DigitalPanel();
        panel.addObserver(observer);

        //when
        panel.updateObservers();

        //then
        verify(observer).update();
    }

    @Test
    public void shouldAddSomePointsAndRemoveNearest() {
        //given
        ComplexPanel panel = new DigitalPanel() {
            @Override
            public Rectangle getVisibleRect() {
                return new Rectangle(400, 400);
            }
        };
        panel.addComplex(createPole(ZERO));
        panel.addComplex(createZero(ONE));
        panel.addComplex(createPole(new Complex(0.01, 0.01)));
        panel.addComplex(createZero(new Complex(-0.01, 0.01)));

        //when
        panel.removeComplex(ZERO);

        //then
        assertThat(panel.complexSize(), is(1));

    }

    @Test
    public void shouldAddComplexZeroFromCenterPoint() {
        //given
        DigitalPanel panel = new DigitalPanel() {
            @Override
            public Rectangle getVisibleRect() {
                return new Rectangle(400, 400);
            }
        };
        Point point = createPoint(200, 200);

        //when
        panel.addPoint(point, Type.ZERO);

        //then
        assertThat(panel.complexSize(), is(2));
        for (ComplexPoint complexPoint : panel.points) {
            assertThat(complexPoint.getComplex(), is(ZERO));
        }
    }

    @Test
    public void shouldAddSomePointsAndRemoveNearestByPoint() {
        //given
        ComplexPanel panel = new DigitalPanel() {
            @Override
            public Rectangle getVisibleRect() {
                return new Rectangle(400, 400);
            }
        };
        panel.addComplex(createPole(ZERO));
        panel.addComplex(createZero(ONE));
        panel.addComplex(createPole(new Complex(0.01, 0.01)));
        panel.addComplex(createZero(new Complex(-0.01, 0.01)));

        //when
        panel.removePoint(new Point(200, 200));

        //then
        assertThat(panel.complexSize(), is(1));

    }

    @Test
    public void shouldAddAppropriateComplexFromGivenPoint1() {
        //given
        DigitalPanel panel = new DigitalPanel() {
            @Override
            public Rectangle getVisibleRect() {
                return new Rectangle(400, 400);
            }
        };
        Point point = createPoint(400 - panel.border, 200); //(1, 0)

        //when
        panel.addPoint(point, Type.ZERO);

        //then
        assertThat(panel.complexSize(), is(2));
        for (ComplexPoint complexPoint : panel.points) {
            assertThat(complexPoint.getComplex(), is(ONE));
        }
    }

    @Test
    public void shouldAddAppropriateComplexFromGivenPoint2() {
        //given
        DigitalPanel panel = new DigitalPanel() {
            @Override
            public Rectangle getVisibleRect() {
                return new Rectangle(400, 400);
            }
        };
        Point point = createPoint(200, 400 - panel.border); //(0, -1)

        //when
        panel.addPoint(point, Type.ZERO);

        //then
        assertThat(panel.complexSize(), is(2));
        assertThat(panel.points.get(0).getComplex(), is(new Complex(0, -1)));
        assertThat(panel.points.get(1).getComplex(), is(new Complex(0, 1)));
    }

    private Point createPoint(int x, int y) {
        Point point = mock(Point.class);
        when(point.getX()).thenReturn(Double.valueOf(x));
        when(point.getY()).thenReturn(Double.valueOf(y));
        return point;
    }

    @Test
    public void shouldAddSomePointsAndNotRemoveFahrest() {
        //given
        ComplexPanel panel = new DigitalPanel() {
            @Override
            public Rectangle getVisibleRect() {
                return new Rectangle(400, 400);
            }
        };
        panel.addComplex(createPole(ZERO));
        panel.addComplex(createZero(ONE));
        panel.addComplex(createPole(new Complex(0.9, 0.1)));
        panel.addComplex(createZero(new Complex(-0.1, 0.9)));

        //when
        panel.removeComplex(ZERO);

        //then
        assertThat(panel.complexSize(), is(3));

    }

    // uruchomienie okna, nie wiem jak to się testuje
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame panel = new JFrame();
                    panel.setSize(400, 400);
                    DigitalPanel complexPanel = new DigitalPanel();
                    complexPanel.addComplex(createPole(new Complex(0, 0)));
                    complexPanel.addComplex(createZero(new Complex(-1, 0)));
                    complexPanel.addComplex(createPole(new Complex(1, 0)));
                    complexPanel.addComplex(createZero(new Complex(0, 1)));
                    complexPanel.addComplex(createPole(new Complex(0, -1)));

                    panel.add(complexPanel);
                    panel.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
