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
import static pl.edu.pk.aipsc.math.ComplexCalculator.resolveDigital;

@RunWith(Parameterized.class)
public class ComplexCalculatorToComplexTest {

    @Parameters(name = "{0} -> point: {1} = {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new Rectangle(100, 100), new Point(0, 50), new Complex(-1, 0)},
                {new Rectangle(100, 100), new Point(50, 0), new Complex(0, 1)},
                {new Rectangle(100, 100), new Point(100, 50), new Complex(1, 0)},
                {new Rectangle(100, 100), new Point(50, 100), new Complex(0, -1)},
                {new Rectangle(100, 100), new Point(50, 50), new Complex(0, 0)},

                {new Rectangle(100, 100), new Point(75, 50), new Complex(0.5, 0)},
                {new Rectangle(100, 100), new Point(50, 75), new Complex(0, -0.5)},
                {new Rectangle(100, 100), new Point(25, 50), new Complex(-0.5, 0)},
                {new Rectangle(100, 100), new Point(50, 25), new Complex(0, 0.5)},

                {new Rectangle(10, 10, 20, 20), new Point(20, 20), new Complex(0, 0)},
                {new Rectangle(10, 10, 20, 20), new Point(20, 10), new Complex(0, 1)},
                {new Rectangle(10, 10, 20, 20), new Point(30, 20), new Complex(1, 0)},
                {new Rectangle(10, 10, 20, 20), new Point(30, 30), new Complex(1, -1)},
                {new Rectangle(10, 10, 20, 20), new Point(10, 10), new Complex(-1, 1)},
                {new Rectangle(10, 10, 20, 20), new Point(25, 25), new Complex(0.5, -0.5)},
                {new Rectangle(10, 10, 20, 20), new Point(15, 15), new Complex(-0.5, 0.5)},
        });
    }

    private Rectangle area;
    private Point givenPoint;
    private Complex expectedComplex;

    public ComplexCalculatorToComplexTest(Rectangle area, Point givenPoint, Complex expectedComplex) {
        this.area = area;
        this.givenPoint = givenPoint;
        this.expectedComplex = expectedComplex;
    }


    @Test
    public void testResolver() {
        //given

        //when
        Complex actualComplex = resolveDigital(area, givenPoint);

        //then
        assertThat(actualComplex, is(expectedComplex));

    }
}
