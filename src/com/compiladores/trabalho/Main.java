package com.compiladores.trabalho;

public class Main {

    public static String numbers = "0123456789";
    public static String fractional = ".";
    public static String exponent = "E";
    public static String signals = "+-";

    private static boolean isNumber(char number) {
        return numbers.contains(String.valueOf(number).toLowerCase());
    }

    private static boolean isFractional(char ch) {
        return fractional.contains(String.valueOf(ch).toLowerCase());
    }

    private static boolean isExponent(char ch) {
        return exponent.contains(String.valueOf(ch).toUpperCase());
    }

    private static boolean isSignalOrDirectNumber(char ch) {
        return isNumber(ch) || signals.contains(String.valueOf(ch).toLowerCase());
    }

    public static void main(String[] args) {
        int errorState = -1, successState = 0;
        String text = "123E4.5";
        int state = 1, i = 0;

        char[] arrayChar = text.toCharArray();

        while (state != errorState && state != successState) {
            switch (state) {
                case 1:
                    if (isNumber(arrayChar[i])) state = 2;
                    else state = -1;
                    i++;
                    break;
                case 2:
                    state = successState;
                    while (i < text.length()) {
                        if (isNumber(arrayChar[i])) i++;
                        else {
                            if (isFractional(arrayChar[i])) state = 1;
                            else if (isExponent(arrayChar[i])) state = 3;
                            else state = errorState;
                            break;
                        }
                    }
                    i++;
                    break;
                case 3:
                    if (isSignalOrDirectNumber(arrayChar[i])) state = 4;
                    else state = errorState;
                    i++;
                    break;
                case 4:
                    state = successState;
                    while (i < text.length()) {
                        if (isNumber(arrayChar[i])) i++;
                        else {
                            state = errorState;
                            break;
                        }
                    }
                    break;
            }
        }

        if (state == errorState) {
            System.out.println("Serie de caracteres inválido!");
        }
        else if (state == successState) {
            System.out.println("Serie de caracteres válido!");
        }
    }
}
