package primes.algo;


public abstract class PrimeAlgorithm {
    protected int max;
    protected boolean multithread;
    public PrimeAlgorithm(int max) {
        this.max = max;
        this.multithread = false;
    }

    public PrimeAlgorithm(int max, boolean multithread) {
        this.max = max;
        this.multithread = multithread;
    }

    public boolean isMultiThreaded() {
        return multithread;
    }

    public void setMultiThreaded(boolean multithread) {
        this.multithread = multithread;
    }

    public abstract int[] find();
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
