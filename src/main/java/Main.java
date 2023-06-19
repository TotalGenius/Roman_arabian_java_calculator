import calculator.RomanArabianCalculator;
import converter.impl.RomanArabianConverterImpl;
import exception.RomanArabianOperationException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws RomanArabianOperationException {
        RomanArabianCalculator calculator = new RomanArabianCalculator(new RomanArabianConverterImpl());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите математическое выражение из 2-х операнд");
        System.out.println("Поддерживаемые операции: +, -, *, /");
        System.out.println("Поддерживаемые системы счисления: римская, арабская");
        String expression = scanner.nextLine();
        String result = calculator.calculate(expression);
        System.out.println("Результат выражения:");
        System.out.println(result);
    }
}
