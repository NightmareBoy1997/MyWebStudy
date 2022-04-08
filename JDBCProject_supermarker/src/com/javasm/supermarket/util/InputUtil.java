package com.javasm.supermarket.util;

import java.util.Scanner;

/**
 * @author: Lisa
 * @className: InputItil
 * @description:
 * @date: 2022/3/17 10:31
 * @version: 0.1
 * @since: jdk11
 */
public class InputUtil {

    private InputUtil() {
    }


    private static Scanner input;

    static {
        input = new Scanner(System.in);
    }

    public static String inputStr() {
        return input.next();
    }


    public static void close() {
        input.close();
    }

    public static int inputInt(String regex, String msg) {
        while (true) {
            System.out.println(msg);
            String str = input.next();
            if (str.matches(regex)) {
                return Integer.parseInt(str);
            }
        }
    }

    public static int inputInt() {
        return input.nextInt();
    }
}
