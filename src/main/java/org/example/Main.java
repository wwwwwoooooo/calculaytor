package org.example;

import java.util.*;

class Main extends CalculatorHelper {
    static int num1, num2;//
    static String operation;
    static int result;
    static boolean numbersArabic = true;

    public static void main(String[] args) {
        Main main = new Main();
        Scanner scan = new Scanner(System.in);
        String inputMain = scan.nextLine();
        main.calc(inputMain);
    }

    public String calc(String inputString) {
        System.out.println("Введите выражение по типу 2 + 2 или римскими числами II + II");
        Scanner numberCalc = new Scanner(System.in);
        inputString = numberCalc.nextLine();
        while (!inputString.isEmpty()) {
            String[] checkNumbers = CalculatorHelper.checkCalc(inputString);
            operation = checkNumbers[1];
            num1 = 0;
            num2 = 0;

            try {
                num1 = Integer.parseInt(checkNumbers[0]);
                num2 = Integer.parseInt(checkNumbers[2]);
            } catch (NumberFormatException e) {
                numbersArabic = false;
            }

            if (numbersArabic) {
                if (num1 > 10 || 10 < num2 || num1 < 1 || 1 > num2) {
                    try {
                        throw new Exception("Вводите числа только от 1 до 10 или от I до X");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    result = helpToOperation(num1, num2, operation);
                }
            } else {
                num1 = rimToArabic(checkNumbers[0]);
                num2 = rimToArabic(checkNumbers[2]);
                result = helpToOperation(num1, num2, operation);
            }

            if (operation.equals("+")) {
                operation = "+";
            }
            if (operation.equals("-")) {
                operation = "-";
            }
            if (operation.equals("*")) {
                operation = "*";
            }
            if (operation.equals("/")) {
                operation = "/";
            }

            if (numbersArabic) {
                System.out.println(result);
            } else {
                String resultRim = parseArabicToRim(result);
                System.out.println(resultRim);
            }

            break;
        }
        return Integer.toString(result);
    }

}


class CalculatorHelper {

    public static int helpToOperation(int num1, int num2, String operationChar) {
        return switch (operationChar) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> throw new IllegalArgumentException("Не верный знак операции");
        };
    }

    public static String[] checkCalc(String check) {
        String[] checkNumbers = check.split(" ");
        if (checkNumbers.length != 3) {
            Scanner input_a_value_again = new Scanner(System.in);
            System.out.println("Введены неверные данные");
            check = input_a_value_again.nextLine();
            return checkCalc(check);
        } else { return checkNumbers; }
    }

    public static String parseArabicToRim (int arabicNum) {
        String[] rim = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String endNumber = rim[arabicNum];
        return endNumber;
    }
    public static int rimToArabic (String rim)  {
        return switch (rim){
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
            default -> throw new IllegalStateException("Неверный формат римских чисел");
        };
    }

}