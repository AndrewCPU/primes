package primes.algo;

import java.util.Arrays;
import java.util.stream.IntStream;

public class EAlgorithm extends PrimeAlgorithm{
    public EAlgorithm(int max) {
        super(max, true);
    }

    @Override
    public int[] find() {
        boolean[] A = new boolean[max];
        Arrays.fill(A, true);
        A[0] = A[1] = false;
        double sqrt = Math.sqrt(max);
        for(int i = 2; i<sqrt; i++){
            if(A[i]){
                for(int j = i * i; j<max; j+= i){
                    A[j] = false;
                }
            }
        }
        return IntStream.range(0, A.length).filter((i) -> A[i]).toArray();
    }
}
