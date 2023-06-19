package converter;

import exception.RomanArabianOperationException;

public interface RomanArabianConverter {
    boolean isRoman(String number);
    boolean isArabian(String number);

    int convertRomanToArabian(String number);

    String convertArabianToRoman(int number) throws RomanArabianOperationException;
}
