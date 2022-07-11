package primes;

import primes.algo.EAlgorithm;
import primes.algo.Multi6Algorithm;

import java.io.File;
import java.io.FileWriter;

public class PrimeDriver {
    private static int max;
    public static void main(String[] args) throws Exception {

        if(args.length != 1){
            throw new Exception("Max search range required in arg0");
        }

        max = Integer.parseInt(args[0]);

        PrimeFinder primeFinder = new PrimeFinder(max, EAlgorithm.class);
        long start = System.currentTimeMillis();
        int[] nums = primeFinder.find();

        System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
        if(nums.length == 0){
            System.out.println("Did not find any primes!");
            return;
        }

        System.out.println("Amount of Primes: " + nums.length);
        System.out.println();
        System.out.println();
        File primes = new File("outputPrimes.txt");
        outputPrimes(nums, primes);
    }


    private static void outputPrimes(int[] primes, File file) throws Exception{
        if(file.exists()){
            file.delete();
        }
        FileWriter output = new FileWriter(file);

        for(int i : primes){
            output.append(i + System.lineSeparator());
        }

        output.close();
        System.out.println("Wrote prime numbers to " + file.getName());
    }
}
