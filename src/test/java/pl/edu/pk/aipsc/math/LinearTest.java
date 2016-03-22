package pl.edu.pk.aipsc.math;

import org.apache.commons.math3.complex.Complex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static pl.edu.pk.aipsc.math.Linear.from;


@RunWith(Parameterized.class)
public class LinearTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // x = 0, (1*x + 0) = 0
                {Complex.ZERO, from(Complex.ONE, Complex.ZERO), Complex.ZERO},
                // x = 0, (1*x + 1) = 1
                {Complex.ZERO, from(Complex.ONE, Complex.ONE), Complex.ONE},
                // x = 1, (1*x + 1) = 2
                {Complex.ONE, from(Complex.ONE, Complex.ONE), new Complex(2)},
                // x = 1, (1*x - 1) = 0
                {Complex.ONE, from(Complex.ONE, Complex.ONE.negate()), Complex.ZERO},
                // x = 1, (-1*x + 1) = 0
                {Complex.ONE, from(Complex.ONE.negate(), Complex.ONE), Complex.ZERO},
                // x = 1, (-1*x - 1) = -2
                {Complex.ONE, from(Complex.ONE.negate(), Complex.ONE.negate()), new Complex(-2)},
                // x = i, (1*x - 1) = -1 + i
                {new Complex(0, 1), from(Complex.ONE, Complex.ONE.negate()), new Complex(-1, 1)},
                // x = 1 - i, (-1*x - i) = -1 - 2i
                {new Complex(1, -1), from(Complex.ONE.negate(), new Complex(0, -1)), new Complex(-1)},
                // // x = 1 - i, (1*x + i) = 1
                {new Complex(1, -1), from(Complex.ONE, new Complex(0, 1)), new Complex(1)},
        });
    }

    private Complex expectResult;
    private Complex actual;
    private Linear linear;

    public LinearTest(Complex actual, Linear linear, Complex expectResult) {
        this.expectResult = expectResult;
        this.actual = actual;
        this.linear = linear;
    }

    @Test
    public void linear() {
        //given
        System.out.println(linear);

        //when
        Complex actualResult = linear.calculate(actual);

        //then
        assertThat(actualResult, is(expectResult));
    }


}
