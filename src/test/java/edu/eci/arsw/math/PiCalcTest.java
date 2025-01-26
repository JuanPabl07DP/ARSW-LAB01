package edu.eci.arsw.math;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 * @author Juan Pablo Daza Pereira
 * @author Nicolas Bernal Fuquene
 */
public class PiCalcTest {

    @Test
    public void piGenTestWithOneThread() throws Exception {
        byte[] digits = PiDigits.getDigits(0, 10, 1);
        byte[] expected = new byte[]{0x2, 0x4, 0x3, 0xF, 0x6, 0xA, 0x8, 0x8, 0x8, 0x5};
        assertArrayEquals(expected, digits);
    }

    @Test
    public void piGenTestWithTwoThreads() throws Exception {
        byte[] digits = PiDigits.getDigits(0, 10, 2);
        byte[] expected = new byte[]{0x2, 0x4, 0x3, 0xF, 0x6, 0xA, 0x8, 0x8, 0x8, 0x5};
        assertArrayEquals(expected, digits);
    }

    @Test
    public void piGenTestWithThreeThreads() throws Exception {
        byte[] digits = PiDigits.getDigits(0, 10, 3);
        byte[] expected = new byte[]{0x2, 0x4, 0x3, 0xF, 0x6, 0xA, 0x8, 0x8, 0x8, 0x5};
        assertArrayEquals(expected, digits);
    }

//    @Test
//    public void shouldCalculateLargeNumberOfDigits() throws Exception {
//        int count = 1000;
//        byte[] digits = PiDigits.getDigits(0, count, 4);
//        assertEquals(count, digits.length);
//    }
//
//    @Test
//    public void shouldCalculateWithOffset() throws Exception {
//        byte[] digits = PiDigits.getDigits(1, 5, 1);
//        byte[] expected = new byte[]{0x4, 0x3, 0xF, 0x6, 0xA};
//        assertArrayEquals(expected, digits);
//    }
//
//    @Test
//    public void shouldHandleUnbalancedThreadDistribution() throws Exception {
//        byte[] digits = PiDigits.getDigits(0, 10, 3); // 10 no es divisible uniformemente por 3
//        byte[] expected = new byte[]{0x2, 0x4, 0x3, 0xF, 0x6, 0xA, 0x8, 0x8, 0x8, 0x5};
//        assertArrayEquals(expected, digits);
//    }
//
//    @Test
//    public void shouldCalculateWithMoreThreadsThanDigits() throws Exception {
//        byte[] digits = PiDigits.getDigits(0, 3, 5);
//        byte[] expected = new byte[]{0x2, 0x4, 0x3};
//        assertArrayEquals(expected, digits);
//    }
//
//    @Test(expected = RuntimeException.class)
//    public void shouldThrowExceptionWithInvalidThreadCount() throws Exception {
//        PiDigits.getDigits(0, 10, 0);
//    }
//
//    @Test(expected = RuntimeException.class)
//    public void shouldThrowExceptionWithNegativeStart() throws Exception {
//        PiDigits.getDigits(-1, 10, 1);
//    }
//
//    @Test(expected = RuntimeException.class)
//    public void shouldThrowExceptionWithNegativeCount() throws Exception {
//        PiDigits.getDigits(0, -10, 1);
//    }
//
//    @Test
//    public void shouldHandleConsecutiveRanges() throws Exception {
//        byte[] firstRange = PiDigits.getDigits(0, 5, 2);
//        byte[] secondRange = PiDigits.getDigits(5, 5, 2);
//        byte[] expected = new byte[]{0x2, 0x4, 0x3, 0xF, 0x6, 0xA, 0x8, 0x8, 0x8, 0x5};
//        byte[] combined = new byte[10];
//        System.arraycopy(firstRange, 0, combined, 0, 5);
//        System.arraycopy(secondRange, 0, combined, 5, 5);
//        assertArrayEquals(expected, combined);
//    }
}
