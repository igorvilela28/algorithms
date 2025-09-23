package leetcode.uber;

import java.util.HashMap;

public class UberAdaSolution {

    public static int product (String roman, String binary)
    {

        int romanInt = romanToNumber(roman);
        int binaryInt = binaryToNumber(binary);

        return romanInt * binaryInt;
    }

    public static int romanToNumber(String roman) {
        HashMap<String, Integer> values = new HashMap<String, Integer>();

        values.put("I", 1);
        values.put("IV", 4);
        values.put("V", 5);
        values.put("IX", 9);
        values.put("X", 10);
        values.put("XL", 40);
        values.put("L", 50);
        values.put("XC", 90);
        values.put("C", 100);
        values.put("CD", 400);
        values.put("D", 500);
        values.put("CM", 900);
        values.put("M", 1000);

        int i = 0;
        int value = 0;

        while (i < roman.length()) {

            // checking for possible two digits value
            if (i <= roman.length() - 2 && values.containsKey(roman.substring(i, i+2))) {
                String romanValue = roman.substring(i, i+2);
                value += values.get(romanValue);
                i+=2;
            } else {
                String romanValue = roman.substring(i, i+1);
                value += values.get(romanValue);
                i++;
            }
        }

        return value;
    }

    public static int binaryToNumber(String binary) {

        return Integer.parseInt(binary, 2);

    }
}
