package pl.edu.pk.aipsc.math;

import org.apache.commons.math3.complex.Complex;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static pl.edu.pk.aipsc.math.Polynomial.polynomial;

public class PolynomialRemovePartTest {

    @Test
    public void shouldRemoveOneFunctionFromNumerator() {
        // given
        // y = x + 2
        Function linear = Linear.withB(new Complex(2));
        // y = -x - 2
        Function otherLinear = Linear.from(new Complex(-1), new Complex(-2));
        // (x+2)*(x+2) / (-x - 2)
        Polynomial poly = polynomial().mul(linear).mul(linear).div(otherLinear);

        // when
        poly.removeFromNumerator(linear);

        // then
        // (x + 2) / (-x - 2) (x=0) -> -1
        Complex result = poly.calculate(Complex.ZERO);
        assertThat(result, is(Complex.ONE.negate()));

    }

    @Test
    public void shouldRemoveAllFunctionFromNumerator() {
        // given
        // y = x + 2
        Function linear = Linear.withB(new Complex(2));
        // y = -x - 2
        Function otherLinear = Linear.from(new Complex(-1), new Complex(-2));
        // (x+2)*(x+2) / (-x - 2) * (x+2)
        Polynomial poly = polynomial().mul(linear).mul(linear).div(otherLinear).div(linear);

        // when
        poly.removeAllFromNumerator(linear);

        // then
        // 1 / (-x - 2) * (x + 2) (x=0) -> -1/4
        Complex result = poly.calculate(Complex.ZERO);
        assertThat(result, is(new Complex(-0.25)));

    }

    @Test
    public void shouldRemoveOneFunctionFromDenominator() {
        // given
        // y = x + 2
        Function linear = Linear.withB(new Complex(2));
        // y = -x - 2
        Function functionInDenominator = Linear.from(new Complex(-1), new Complex(-2));
        // (x+2)*(x+2) / (-x - 2) * (-x - 2)
        Polynomial poly = polynomial().mul(linear).mul(linear).div(functionInDenominator).div(functionInDenominator);

        // when
        poly.removeFromDenominator(functionInDenominator);

        // then
        // (x + 2) * (x + 2) / (-x - 2) (x=0) -> -2
        Complex result = poly.calculate(Complex.ZERO);
        assertThat(result, is(new Complex(-2)));

    }

    @Test
    public void shouldRemoveAllFunctionFromDenominator() {
        // given
        // y = x + 2
        Function linear = Linear.withB(new Complex(2));
        // y = -x - 2
        Function otherLinear = Linear.from(new Complex(-1), new Complex(-2));
        // (x+2)*(-x - 2) / (x+2) * (-x - 2)
        Polynomial poly = polynomial().mul(otherLinear).mul(linear).div(otherLinear).div(linear);

        // when
        poly.removeAllFromDenominator(linear);

        // then
        // (x + 2) * (-x-2) / (-x-2) (x=0) -> 2
        Complex result = poly.calculate(Complex.ZERO);
        assertThat(result, is(new Complex(2)));

    }

    @Test
    public void shouldRemoveAllFunctionFromPolynomial() {
        // given
        // y = x + 2
        Function linear = Linear.withB(new Complex(2));
        // y = -x - 2
        Function otherLinear = Linear.from(new Complex(-1), new Complex(-2));
        // (x+2)*(x+2) / (-x - 2) * (x+2)
        Polynomial poly = polynomial().mul(linear).mul(linear).div(otherLinear).div(linear);

        // when
        poly.removeAll(linear);

        // then
        // 1 / (-x - 2) (x=0) -> -1/2
        Complex result = poly.calculate(Complex.ZERO);
        assertThat(result, is(new Complex(-0.5)));

    }

}
