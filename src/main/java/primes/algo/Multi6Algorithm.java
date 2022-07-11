package primes.algo;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Multi6Algorithm extends PrimeAlgorithm {
    private double squareMax;
    private final int startingPoint = 5;
    private int[] arr1;
    private int[] arr2;

    public Multi6Algorithm(int max) {
        super(max, true);
        configure();
    }

    public void configure() {
        squareMax = Math.sqrt(max);
        arr1 = new int[max + 6];
        arr2 = new int[max + 6];
        Arrays.fill(arr1, 0);
        Arrays.fill(arr2, 1);
        if (max >= 2)
            arr1[2] = arr2[2] = 1;
        if (max >= 3)
            arr1[3] = arr2[3] = 1;
    }

    private void runCalculation(int n){
        int i = (startingPoint + (n * 6));
        for (int j = 0; true; j++) {
            int pieceA = i + 2;
            int pieceB = j + 1;
            int pieceC = pieceA * pieceA;
            int pieceD = 6 * i;
            int pieceE = 6 * pieceA;

            int temp1 = pieceD * pieceB + i;
            int temp2 = ((pieceD * j) + i * i);
            int temp3 = ((pieceE * j) + pieceC);
            int temp4 = ((pieceE * pieceB) + pieceC - 2 * pieceA);

            boolean t1M = temp1 <= max;
            boolean t2M = temp2 <= max;
            boolean t3M = temp3 <= max;
            boolean t4M = temp4 <= max;

            if (t1M) {
                arr2[temp1] = 0;
            }
            if (t2M) {
                arr2[temp2] = 0;
            }
            if (t3M) {
                arr2[temp3] = 0;
            }
            if (t4M) {
                arr2[temp4] = 0;
            }

            int flag = ((!t1M) ? 1 : 0) + ((!t2M) ? 1 : 0) + ((!t3M) ? 1 : 0) + ((!t4M) ? 1 : 0);

            if (flag == 4) {
                break;
            }
        }
    }

    private int[] impl() {
        IntStream.range(0, ((max + 1) - startingPoint) / 6).parallel().forEach((i) -> {
            int nx = i * 6;
            arr1[startingPoint + nx] = arr1[startingPoint + (nx) + 2] = 1;
        });
        IntStream stream = IntStream.range(0, (int) (((squareMax + 1) - startingPoint) / 6));
        if (this.isMultiThreaded()) {
            stream = stream.parallel();
        }
        stream.forEach(this::runCalculation);

        return IntStream.range(2, arr1.length).filter(p -> (arr1[p] + arr2[p]) == 2).toArray();
    }

    @Override
    public int[] find() {
        return impl();
    }
}