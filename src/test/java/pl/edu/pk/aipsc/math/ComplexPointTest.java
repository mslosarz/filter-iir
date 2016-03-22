package pl.edu.pk.aipsc.math;

import org.apache.commons.math3.complex.Complex;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static pl.edu.pk.aipsc.math.ComplexPoint.*;

public class ComplexPointTest {

    @Test
    public void shouldCheckIsCreatedZero() {
        //given
        ComplexPoint point;

        // when
        point = createZero(Complex.ONE);

        // then
        assertThat(point.getComplex(), is(Complex.ONE));
        assertThat(point.getType(), is(Type.ZERO));
    }

    @Test
    public void shouldCheckIsCreatedPole() {
        //given
        ComplexPoint point;

        // when
        point = createPole(Complex.ONE);

        // then
        assertThat(point.getComplex(), is(Complex.ONE));
        assertThat(point.getType(), is(Type.POLE));
    }
}
