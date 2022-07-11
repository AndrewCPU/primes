package primes;

import primes.algo.PrimeAlgorithm;

import java.lang.reflect.InvocationTargetException;

public class PrimeFinder {
    protected PrimeAlgorithm primeAlgorithm;
    public <M extends PrimeAlgorithm> PrimeFinder(int max, Class<M> algo) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        primeAlgorithm = (PrimeAlgorithm) algo.getConstructors()[0].newInstance(max);
    }

    public int[] find() {
        return (primeAlgorithm.find());
    }
}
