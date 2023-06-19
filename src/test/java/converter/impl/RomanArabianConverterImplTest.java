package converter.impl;

import converter.RomanArabianConverter;
import exception.RomanArabianOperationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RomanArabianConverterImplTest {

    RomanArabianConverter romanArabianConverter = new RomanArabianConverterImpl();

    @Test
    void isRoman() {
        boolean expected1 = true;
        boolean actual1 = romanArabianConverter.isRoman("VI");
        assertEquals(expected1, actual1);

        boolean expected2 = false;
        boolean actual2 = romanArabianConverter.isRoman("VI3");
        assertEquals(expected2,actual2);
    }

    @Test
    void isArabian() {
        boolean expected1 = true;
        boolean actual1 = romanArabianConverter.isArabian("543");
        assertEquals(expected1, actual1);

        boolean expected2 = false;
        boolean actual2 = romanArabianConverter.isArabian("4j32");
        assertEquals(expected2,actual2);
    }

    @Test
    void convertRomanToArabian() {
        int expected = 11;
        int actual = romanArabianConverter.convertRomanToArabian("XI");
        assertEquals(expected, actual);
    }

    @Test
    void convertArabianToRoman() throws RomanArabianOperationException {
        String expected = "XIV";
        String actual = romanArabianConverter.convertArabianToRoman(14);
        assertEquals(expected, actual);

    }

    @Test
    void convertArabianToRomanExceptionTest() {
        Throwable thrown = assertThrows(RomanArabianOperationException.class, () -> romanArabianConverter.convertArabianToRoman(-5));
        String expectedExceptionMessage = "в римской системе нет отрицательных чисел";
        assertEquals(expectedExceptionMessage, thrown.getMessage());
    }
}