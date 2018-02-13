package fib;

import java.util.*;

public class Fib {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      int n = 999999;
      int l = 10000;
      int counter = 1;

        // initially assume all integers are prime
        boolean[] isPrime = new boolean[n+1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        // mark non-primes <= n using Sieve of Eratosthenes
        for (int factor = 2; factor*factor <= n; factor++) {

            // if factor is prime, then mark multiples of factor as nonprime
            // suffices to consider mutiples factor, factor+1, ...,  n/factor
            if (isPrime[factor]) {
                for (int j = factor; factor*j <= n; j++) {
                    isPrime[factor*j] = false;
                }
            }
        }
        ArrayList<Integer> primeNumbers = new ArrayList<Integer>();  
        // count primes
        int primes = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i] && i >= l) {
                primeNumbers.add(i);
                primes++;
            }
        }
        System.out.println("The number of primes <= " + n + " and "+ l + " is " + primes);
        Collections.reverse(primeNumbers);
        int x = 0;
        int y = 0;
        long firstPrime = 0;
        long secondPrime = 0;
        long palindromeBiggest = 0;
        while (x <= primeNumbers.size() - 1) {
           long i = primeNumbers.get(x);
           long j = primeNumbers.get(y); 
           int size = primeNumbers.size() - 1;
           long result = i * j;
           if (result > palindromeBiggest && isPalindrome(result)) {
                palindromeBiggest = result;
                firstPrime = i;
                secondPrime = j;
                System.out.println(counter + ". palindrome: " + palindromeBiggest + " num1: " + i + " num2: " + j);
                counter++;
           } else {
                    y++;
                    if (y == size) {
                        x++;
                        y = 0;
                        if (x == size) {
                            break;
                        }
                    }
                }
           
        }
        
        System.out.println("Biggest palindrome is " + palindromeBiggest + " first number " + firstPrime + " second number " + secondPrime );
        System.out.println("The biggest divisor of " + palindromeBiggest + " is " + findDivisor(palindromeBiggest));
    }
    
    public static boolean isPalindrome (long number) {
        boolean palindrome = false;
        String a = Long.toString(number);
        String b = "";
        int x = a.length() - 1;
        int y = x;
        while ( y >= 0) {
            char o = a.charAt(y);
            b += o;
            y--;
        }
        if (a.equals(b)) {
            palindrome = true;
        } 
        return palindrome;
    }
    
   public static long findDivisor(long x){
       long result = 0;
       for (int i = 1; i <= x/2; i++){
           long z = x % i;
//            if (z != 0){
//                continue;
//            } else 
            if (z == 0){
                //if there's no break statement, we can print each divisor from here:
                result = i;
                System.out.println("One divisor of " + x + " is: " + i);
                //Due to break, method finds only the first divisor, other than 1. If break is removed, it would find the biggest divisor.
                //break;
            }
       }
       return result;
   }
}