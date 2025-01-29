package edu.eci.arsw.math;

import edu.eci.arsw.threads.PiDigitsThread;

/**
 *
 * @author Juan Pablo Daza Pereira
 * @author Nicolas Bernal Fuquene
 */
public class PiDigits {

    private static int DigitsPerSum = 8;
    private static double Epsilon = 1e-17;


    /**
     * Returns a range of hexadecimal digits of pi.
     * @param start The starting location of the range.
     * @param count The number of digits to return
     * @param numThreads Number of threads to use for calculation
     * @return An array containing the hexadecimal digits.
     */
    public static byte[] getDigits(int start, int count, int numThreads) {
        if (start < 0 || count < 0 || numThreads <= 0) {
            throw new RuntimeException("Invalid arguments");
        }

        byte[] digits = new byte[count];
        //Calculo de los dígitos por hilo
        int digitsPerThread = count / numThreads;
        int remainingDigits = count % numThreads;
        //Crea e inicializa los hilos
        PiDigitsThread[] threads = new PiDigitsThread[numThreads];
        int currentStart = start;

        for (int i = 0; i < numThreads; i++) {
            int threadDigits = digitsPerThread + (i < remainingDigits ? 1 : 0); // Agrega un dígito adicional a algunos hilos
            threads[i] = new PiDigitsThread(currentStart, threadDigits);
            threads[i].start();
            currentStart += threadDigits;
        }

        //
        try {
            int currentPosition = 0;
            for (int i = 0; i < numThreads; i++) {
                threads[i].join();
                byte[] threadDigits = threads[i].getDigits();
                System.arraycopy(threadDigits, 0, digits, currentPosition, threadDigits.length);
                currentPosition += threadDigits.length;
            }
        }catch (InterruptedException e) {
            throw new RuntimeException("Error, calculation interrupted ",e);
        }
        return digits;
    }
    /**
     * Calculates a range of hexadecimal digits of pi
     */
    public static byte[] calculateDigits (int start, int count) {
        byte[] digits = new byte[count];
        double sum = 0;

        for (int i = 0; i < count; i++) {
            if (i % DigitsPerSum == 0) {
                sum = 4 * sum(1, start) - 2 * sum(4, start) - sum(5, start) - sum(6, start);
                start += DigitsPerSum;
            }
            sum = 16 * (sum - Math.floor(sum));
            digits[i] = (byte) sum;
        }
        return digits;
    }

    private static double sum(int m, int n) {
        double sum = 0;
        int d = m;
        int power = n;

        while (true) {
            double term;

            if (power > 0) {
                term = (double) hexExponentModulo(power, d) / d;
            } else {
                term = Math.pow(16, power) / d;
                if (term < Epsilon) {
                    break;
                }
            }

            sum += term;
            power--;
            d += 8;
        }

        return sum;
    }

    private static int hexExponentModulo(int p, int m) {
        int power = 1;
        while (power * 2 <= p) {
            power *= 2;
        }

        int result = 1;

        while (power > 0) {
            if (p >= power) {
                result *= 16;
                result %= m;
                p -= power;
            }

            power /= 2;

            if (power > 0) {
                result *= result;
                result %= m;
            }
        }

        return result;
    }
}
