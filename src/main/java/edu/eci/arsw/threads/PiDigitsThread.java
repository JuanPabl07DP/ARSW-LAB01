package edu.eci.arsw.threads;

import edu.eci.arsw.math.PiDigits;

/**
 *
 * @author Juan Pablo Daza Pereira
 * @author Nicolas Bernal Fuquene
 */
public class PiDigitsThread extends Thread {
    private final int start;
    private final int count;
    private byte[] digits;

    /**
     * Creates a new thread to calculate PI digits
     * @param start The starting position for this thread's calculation
     * @param count The number of digits this thread should calculate
     */
    public PiDigitsThread(int start, int count) {
        this.start = start;
        this.count = count;
    }

    @Override
    public void run() {
        digits = PiDigits.calculateDigits(start, count);
    }

    /**
     * Gets the calculated digits
     * @return Array of calculated hexadecimal digits
     */
    public byte[] getDigits() {
        return digits;
    }
}
