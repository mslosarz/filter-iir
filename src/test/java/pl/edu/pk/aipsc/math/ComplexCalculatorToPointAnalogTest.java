package pl.edu.pk.aipsc.math;

import org.apache.commons.math3.complex.Complex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.awt.*;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static pl.edu.pk.aipsc.math.ComplexCalculator.resolveAnalog;

@RunWith(Parameterized.class)
public class ComplexCalculatorToPointAnalogTest {

    @Parameters(name = "point: {1} = {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new Rectangle(100, 100), new Complex(-100, 0), new Point(0, 50)},
                {new Rectangle(100, 100), new Complex(0, 100), new Point(50, 0)},
                {new Rectangle(100, 100), new Complex(100, 0), new Point(100, 50)},
                {new Rectangle(100, 100), new Complex(0, -100), new Point(50, 100)},
                {new Rectangle(100, 100), new Complex(0, 0), new Point(50, 50)},
                {new Rectangle(10, 10), new Complex(100, 0), new Point(10, 5)},

                {new Rectangle(100, 100), new Complex(50, 0), new Point(75, 50)},
                {new Rectangle(100, 100), new Complex(0, -50), new Point(50, 75)},
                {new Rectangle(100, 100), new Complex(-50, 0), new Point(25, 50)},
                {new Rectangle(100, 100), new Complex(0, 50), new Point(50, 25)},
                {new Rectangle(1000, 1000), new Complex(0, 50), new Point(500, 250)},
        });
    }

    private Rectangle area;
    private Point expectedPoint;
    private Complex givenComplex;

    public ComplexCalculatorToPointAnalogTest(Rectangle area, Complex givenComplex, Point expectedPoint) {
        this.area = area;
        this.expectedPoint = expectedPoint;
        this.givenComplex = givenComplex;
    }


    @Test
    public void testResolver() {
        //given

        //when
        Point actualPoint = resolveAnalog(area, givenComplex, 100);

        //then
        assertThat(actualPoint, is(expectedPoint));

    }
}
