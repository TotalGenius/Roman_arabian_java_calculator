package calculator;

import converter.impl.RomanArabianConverterImpl;
import exception.RomanArabianOperationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RomanArabianCalculatorTest {

    RomanArabianCalculator romanArabianCalculator = new RomanArabianCalculator(new RomanArabianConverterImpl());

    @Test
    void calculateRomanNumbersTest() throws RomanArabianOperationException {
        String expected = "15+13=28";
        String actual = romanArabianCalculator.calculate("15+13");
        assertEquals(expected,actual);
    }

    @Test
    void calculateArabianNumbersTest() throws RomanArabianOperationException {
        String expected = "XX+VI=XXVI";
        String actual = romanArabianCalculator.calculate("XX+VI");
        assertEquals(expected,actual);
    }

    @Test
    void calculateIncorrectOperation() throws RomanArabianOperationException {
        Throwable thrown = assertThrows(RomanArabianOperationException.class, () -> romanArabianCalculator.calculate("5%12"));
        String expectedExceptionMessage = "Введена неверная операция";
        assertEquals(expectedExceptionMessage, thrown.getMessage());
    }

    @Test
    void calculateDifferentNotation() throws RomanArabianOperationException {
        Throwable thrown = assertThrows(RomanArabianOperationException.class, () -> romanArabianCalculator.calculate("5+X"));
        String expectedExceptionMessage = "используются одновременно разные системы счисления";
        assertEquals(expectedExceptionMessage, thrown.getMessage());
    }

    @Test
    void calculateMoreThenTwoOperands() throws RomanArabianOperationException {
        Throwable thrown = assertThrows(RomanArabianOperationException.class, () -> romanArabianCalculator.calculate("5+5+5"));
        String expectedExceptionMessage = "формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
        assertEquals(expectedExceptionMessage, thrown.getMessage());
    }

    @Test
    void calculateMoreThenTwoOperands2() throws RomanArabianOperationException {
        Throwable thrown = assertThrows(RomanArabianOperationException.class, () -> romanArabianCalculator.calculate("I+V+X"));
        String expectedExceptionMessage = "формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
        assertEquals(expectedExceptionMessage, thrown.getMessage());
    }

    @Test
    void calculateOnlyOneOperand() throws RomanArabianOperationException {
        Throwable thrown = assertThrows(RomanArabianOperationException.class, () -> romanArabianCalculator.calculate("1"));
        String expectedExceptionMessage = "Введена неверная операция";
        assertEquals(expectedExceptionMessage, thrown.getMessage());
    }




}