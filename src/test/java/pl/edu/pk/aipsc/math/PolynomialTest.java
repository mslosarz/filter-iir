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
import static pl.edu.pk.aipsc.math.Linear.withB;
import static pl.edu.pk.aipsc.math.Polynomial.polynomial;


@RunWith(Parameterized.class)
public class PolynomialTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // z1 = 0, (z1 - 1) = -1
                {Complex.ZERO,
                        polynomial().mul(withB(Complex.ONE.negate())),
                        new Complex(-1)},
                // z1 = 1, (z1 + 1)*(z1 - 1) = 0
                {Complex.ONE,
                        polynomial().mul(withB(Complex.ONE)).mul(withB(Complex.ONE.negate())),
                        Complex.ZERO},
                // z1 = i, (z1 + 1)*(z1 - i) = 0
                {new Complex(0, 1),
                        polynomial().mul(withB(Complex.ONE)).mul(withB(new Complex(0, -1))),
                        Complex.ZERO},
                // z1 = 2 - i, (z1 - 2)*(z1 - 2) = 1
                {new Complex(2, -1),
                        polynomial().mul(withB(new Complex(-2))).mul(withB(new Complex(-2))),
                        Complex.ONE.negate()},
                // z1 = 0, 1/(z1 - 1)  = -1
                {Complex.ZERO,
                        polynomial().div(withB(new Complex(-1))),
                        Complex.ONE.negate()},
                // z1 = 0, 1/(z1 + 2)  = 1/2
                {Complex.ZERO,
                        polynomial().div(withB(new Complex(2))),
                        new Complex(1. / 2)},
                // z1 = 0, 1/(z1 + 0)  = INF
                {Complex.ZERO,
                        polynomial().div(withB(Complex.ZERO)),
                        new Complex(Double.MAX_VALUE)},
                // z1 = 1, (z1 - 1)/(z1 - 1)  = INF
                {Complex.ONE,
                        polynomial().div(withB(new Complex(-1))).mul(withB(new Complex(-1))),
                        new Complex(Double.MAX_VALUE)}
        });
    }

    private Complex expectedResult;
    private Complex actual;
    private Polynomial polynomial;

    public PolynomialTest(Complex actual, Polynomial polynomial, Complex result) {
        this.expectedResult = result;
        this.actual = actual;
        this.polynomial = polynomial;
    }

    @Test
    public void polynomialTest() {
        //given
        System.out.println(polynomial);

        //when
        Complex actualResult = polynomial.calculate(actual);

        //then
        assertThat(actualResult, is(expectedResult));
    }


}
