import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import primes.PrimeFinder;
import primes.algo.Multi6Algorithm;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimeTest {
    private static final int MAX_SEARCH = 1000000;
    private int[] found;
    private int[] official;

    @BeforeEach
    public void findNumbers() throws Exception{
        PrimeFinder primeFinder = new PrimeFinder(MAX_SEARCH, Multi6Algorithm.class);
        found = primeFinder.find();
        String data = Files.readString(Path.of("primes.txt"));
        String[] lines = data.split("\n");
        official = Arrays.stream(lines).parallel().map(line -> Integer.parseInt(line.replaceAll(" ", "").split(",")[1])).filter(i -> i <= MAX_SEARCH).mapToInt(Integer::intValue).toArray();
    }

    @Test
    public void timeTest () throws Exception {
        List<Long> durations = new ArrayList<>();
        for(int i =0 ; i<= 500; i++){
            PrimeFinder primeFinder = new PrimeFinder(MAX_SEARCH, Multi6Algorithm.class);
            long start = System.currentTimeMillis();
            int[] arr = primeFinder.find();
            durations.add(System.currentTimeMillis() - start);
        }

        double average = LongStream.of(durations.stream().mapToLong(Long::longValue).toArray()).average().getAsDouble();
        System.out.println("Average time to compute primes 1-" + MAX_SEARCH + ": " + average + "ms");
    }

    @Test
    public void testNumbers() throws Exception{
        assertEquals(official.length, found.length, "Did not find the correct amount of primes.");
        for(int i = 0; i< official.length; i++){
            assertEquals(official[i], found[i], "Mismatch value. Found " + found[i] + ", Correct " + official[i]);
        }
    }
}
