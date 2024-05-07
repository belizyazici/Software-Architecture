import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface PrimeInterface{
    String findPrimes(int limit);
}
class SimplePrimeFinder implements PrimeInterface {
    public String findPrimes(int limit) {
        String primes = "";
        for (int number = 2; number <= limit; number++) {
            if (isPrime(number)) {
                primes = primes + " " + number;
            }
        }
        return primes;
    }

    private boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}

// Testing the prime finders
class Prime {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        PrimeInterface simplePrimeFinder = new SimplePrimeFinder();
        System.out.println("Please enter a number to find prime numbers from 0 to your number: ");
        int limit = scan.nextInt();
        System.out.println("Primes found by Simple Prime Finder: " + simplePrimeFinder.findPrimes(limit));
    }
}


interface PrimeFinder {
    List<Integer> sievePrimes(int limit);
}
class SievePrime implements PrimeFinder {
    public List<Integer> sievePrimes(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        List<Integer> primes = new ArrayList<>();

        for (int number = 2; number * number <= limit; number++) {
            if (isPrime[number]) {
                for (int multiple = number * number; multiple <= limit; multiple += number) {
                    isPrime[multiple] = false;
                }
            }
        }

        for (int number = 2; number <= limit; number++) {
            if (isPrime[number]) {
                primes.add(number);
            }
        }

        return primes;
    }
}
class ConnectorAdapter extends SievePrime implements PrimeInterface{

    @Override
    public String findPrimes(int limit) {
        String prime = String.valueOf(sievePrimes(5));
        return prime;
    }
}
