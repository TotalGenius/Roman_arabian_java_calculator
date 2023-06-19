package calculator;

import converter.RomanArabianConverter;
import exception.RomanArabianOperationException;

public class RomanArabianCalculator {
    private RomanArabianConverter romanArabianConverter;
    private final String[] operations = {"+", "-", "/", "*"};
    private final String[] regexOperations = {"\\+", "-", "/", "\\*"};

    public RomanArabianCalculator(RomanArabianConverter romanArabianConverter) {
        this.romanArabianConverter = romanArabianConverter;
    }

    public String calculate(String expression) throws RomanArabianOperationException {
        int operationIndex = findOperationIndex(expression);
        String[] operands = getOperands(expression, operationIndex);
        if (operands.length != 2) {
            throw new RomanArabianOperationException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        int number1;
        int number2;
        int result;
        boolean isRoman;
        if (romanArabianConverter.isRoman(operands[0]) && romanArabianConverter.isRoman(operands[1])) {
            number1 = romanArabianConverter.convertRomanToArabian(operands[0]);
            number2 = romanArabianConverter.convertRomanToArabian(operands[1]);
            isRoman = true;
        } else if (romanArabianConverter.isArabian(operands[0]) && romanArabianConverter.isArabian(operands[1])) {
            number1 = Integer.parseInt(operands[0]);
            number2 = Integer.parseInt(operands[1]);
            isRoman = false;
        } else {
            throw new RomanArabianOperationException("используются одновременно разные системы счисления");
        }

        switch (operations[operationIndex]) {
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "/":
                result = number1 / number2;
                break;
            default:
                result = number1 * number2;


        }
        if (isRoman) {
            String romanNumber1 = romanArabianConverter.convertArabianToRoman(number1);
            String romanNumber2 = romanArabianConverter.convertArabianToRoman(number2);
            String romanResult = romanArabianConverter.convertArabianToRoman(result);
            return romanNumber1 + operations[operationIndex] + romanNumber2 + "=" + romanResult;
        } else {
            return number1 + operations[operationIndex] + number2 + "=" + result;
        }

    }

    private int findOperationIndex(String expression) throws RomanArabianOperationException {
        int index = -1;
        for (int i = 0; i < operations.length; i++) {
            if (expression.contains(operations[i])) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new RomanArabianOperationException("Введена неверная операция");
        }
        return  index;
    }

    private String[] getOperands(String expression, int operationIndex) {
        return expression.split(regexOperations[operationIndex]);
    }


}
