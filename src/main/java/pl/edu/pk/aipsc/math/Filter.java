package pl.edu.pk.aipsc.math;

import org.apache.commons.math3.complex.Complex;
import pl.edu.pk.aipsc.common.Observable;
import pl.edu.pk.aipsc.common.Observer;

public interface Filter extends Observable, Observer {

    public abstract Complex[] fft(Complex[] args);

    public abstract Complex[] filter(Complex[] signal);

}