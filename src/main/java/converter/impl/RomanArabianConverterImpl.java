package converter.impl;

import converter.RomanArabianConverter;
import exception.RomanArabianOperationException;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RomanArabianConverterImpl implements RomanArabianConverter {
    private final Map<Character, Integer> romanArabianMap;

    private final TreeMap<Integer, String> arabianRomanMap;

    public RomanArabianConverterImpl() {
        romanArabianMap = new HashMap<>();
        romanArabianMap.put('I', 1);
        romanArabianMap.put('V', 5);
        romanArabianMap.put('X', 10);
        romanArabianMap.put('L', 50);
        romanArabianMap.put('C', 100);
        romanArabianMap.put('D', 500);
        romanArabianMap.put('M', 1000);

        arabianRomanMap = new TreeMap<>();
        arabianRomanMap.put(1000, "M");
        arabianRomanMap.put(900, "CM");
        arabianRomanMap.put(500, "D");
        arabianRomanMap.put(400, "CD");
        arabianRomanMap.put(100, "C");
        arabianRomanMap.put(90, "XC");
        arabianRomanMap.put(50, "L");
        arabianRomanMap.put(40, "XL");
        arabianRomanMap.put(10, "X");
        arabianRomanMap.put(9, "IX");
        arabianRomanMap.put(5, "V");
        arabianRomanMap.put(4, "IV");
        arabianRomanMap.put(1, "I");
    }


    @Override
    public boolean isRoman(String number) {
        for (int i = number.length() - 1; i >= 0; i--) {
            if (!romanArabianMap.containsKey(number.charAt(i))) {
               // System.out.println(number+ " не римское число");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isArabian(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            //System.out.println(number+ " не арабское число");
            return false;
        }
    }

    @Override
    public int convertRomanToArabian(String roman) {
        int lastPosition = roman.length() - 1;
        char[] romanArr = roman.toCharArray();
        int result;
        int arabian;
        result = romanArabianMap.get(romanArr[lastPosition]);
        for (int i = lastPosition - 1; i >= 0; i--) {
            arabian = romanArabianMap.get(romanArr[i]);
            if (arabian < romanArabianMap.get(romanArr[i + 1])) {
                result -= arabian;
            } else {
                result += arabian;
            }
        }

        return result;
    }

    @Override
    public String convertArabianToRoman(int number) throws RomanArabianOperationException {
        if (number < 1) {
            throw new RomanArabianOperationException("в римской системе нет отрицательных чисел");
        }
        StringBuilder roman = new StringBuilder();
        int arabianNumber;
        do{
            arabianNumber = arabianRomanMap.floorKey(number);
            roman.append(arabianRomanMap.get(arabianNumber));
            number-=arabianNumber;
        }while (number!=0 && number >0);
        return roman.toString();
    }
}
