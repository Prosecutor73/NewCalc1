import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите арифметическое выражение: ");
        String input = scanner.nextLine();
        System.out.print("Результат арифметического выражения: " + input);
        calc(input);
    }

    public static String calc(String input) {

        String mark = " ";
        char[] symbol = new char[10];
        char operator = '+';
        for (int i = 1; i < input.length(); i++) {
            symbol[i] = input.charAt(i);
            switch (symbol[i]) {
                case '+' -> {
                    operator = '+';
                    mark = "\\+";
                }
                case '-' -> {
                    operator = '-';
                    mark = "-";
                }
                case '*' -> {
                    operator = '*';
                    mark = "\\*";
                }
                case '/' -> {
                    operator = '/';
                    mark = "/";
                }
            }
        }

        String[] numbers = input.split(mark);
        if (numbers.length > 2) {
            throw new ArrayIndexOutOfBoundsException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *).");
        } else if (numbers.length < 2) {
            throw new ArrayIndexOutOfBoundsException("Cтрока не является математической операцией");
        }
        int num1 = numbersConversion(numbers[0]);
        int num2 = numbersConversion(numbers[1]);
        if (num1 == 0 | num2 == 0) {
            try {
                num1 = Integer.parseInt(numbers[0]);
                num2 = Integer.parseInt(numbers[1]);
                if (num1 > 10 | num2 > 10 | num1 < 1 | num2 < 1) {
                    throw new NumberFormatException(" Неправильный формат числа: допустим ввод арабских или римских цифр от 1 до 10 без пробелов.");
                }
                int arabianNumber = calculate(num1, num2, operator);
                System.out.print("=" + arabianNumber);
            } catch (NumberFormatException e) {
                System.out.println(" Неправильный формат числа: допустим ввод арабских или римских цифр от 1 до 10.");
            } catch (ArithmeticException e) {
                System.out.println("= На ноль делить нельзя!");
            }
        } else {
            try {
                int result = calculate(num1, num2, operator);
                if (result < 1){
                    throw new RuntimeException (" Результат работы калькулятора с римскими числами не может быть меньше единицы");
                } else {
                    String resultRoman = romanNumbers(result);
                    System.out.print ("=" + resultRoman);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(" Результатом работы калькулятора с римскими числами могут быть только положительные числа!");
            }
        }
        return String.valueOf(calculate(num1, num2, operator));
    }

    public static int calculate(int x1, int x2, char op) {
        int result = 0;
        switch (op) {
            case '+' -> result = x1 + x2;
            case '-' -> result = x1 - x2;
            case '*' -> result = x1 * x2;
            case '/' -> result = x1 / x2;
            default -> {
            }
        }
        return result;
    }

    public static int numbersConversion(String roman) {
        return switch (roman) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> 0;
        };
    }

    public static String romanNumbers(int arabian) {
        String[] romanList = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX",
                "*****", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII",
                "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX",
                "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII",
                "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        return romanList[arabian];
    }

}