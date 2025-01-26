package edu.eci.arsw.math;

import edu.eci.arsw.threads.PiDigitsThread;

/**
 *
 * @author hcadavid
 * @author Juan Pablo Daza Pereira
 * @author Nicolas Bernal Fuquene
 */
public class PiDigits {

    private static int DigitsPerSum = 8;
    private static double Epsilon = 1e-17;

    public static byte[] getDigits(int start, int count, int N) throws InterruptedException {
        if (start < 0 || count < 0 || N <= 0) {
            throw new RuntimeException("Invalid Interval");
        }

        byte[] result = new byte[count];
        PiDigitsThread[] threads = new PiDigitsThread[N];

        int digitsPerThread = count / N;
        int remainingDigits = count % N;
        int currentStart = start;

        // Creamos e iniciamos los hilos
        for (int i = 0; i < N; i++) {
            int threadDigits = digitsPerThread + (i == N - 1 ? remainingDigits : 0);
            threads[i] = new PiDigitsThread(currentStart, threadDigits);
            threads[i].start();
            currentStart += threadDigits;
        }

        // Espera los hilos y combina los resultados
        int currentPosition = 0;
        for (int i = 0; i < N; i++) {
            threads[i].join();
            byte[] threadDigits = threads[i].getDigits();
            System.arraycopy(threadDigits, 0, result, currentPosition, threadDigits.length);
            currentPosition += threadDigits.length;
        }
        return result;
    }

    // Calculo de dígitos
    public static byte[] getDigits(int start, int count){
        if (start < 0 || count < 0) {
            throw new RuntimeException("Invalid Interval");
        }
        byte[] digits = new byte[count];
        double sum = 0;

        for (int i = 0; i < count; i++) {
            if (i % DigitsPerSum == 0){
                sum = 4 * sum(1, start) - 2 * sum(4,start)
                        - sum(5, start) - sum(6,start);
                start += DigitsPerSum;
            }
            sum = 16 * (sum - Math.floor(sum));
            digits[i] = (byte) sum;
        }
        return digits;
    }

    // Calculos de las sumas con base en BBP
    private static double sum(int m, int n) {
        double sum = 0;
        int d = m;
        int power = n;

        while (true){
            double term;
            if (power > 0){
                term = (double) hexExponentModulo(power, d) / d;
            }else {
                term = Math.pow(16,power) / d;
                if (term < Epsilon){
                    break;
                }
            }
            sum += term;
            power--;
            d += 8;
        }
        return sum;
    }

    // Exponenciación en base 16
    private static int hexExponentModulo(int p, int m){
        int power = 1;
        while (power * 2 <= p){
            power *= 2;
        }
        int result = 1;
        while (power > 0){
            if (p >= power){
                result *= 16;
                result %= m;
                p -= power;
            }
            power /= 2;
            if (power > 0){
                result *= result;
                result %= m;
            }
        }
        return result;
    }
}
