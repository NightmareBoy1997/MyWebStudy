package com.javasm.supermarket.util;

import java.util.Scanner;

/**
 * @projectName: supermarket
 * @package: com.javasm.supermarket.util
 * @author: Nightmare970701
 * @description:
 * @since:
 * @version: JDK11
 * @create: 2022-03-19 22:01
 */
public class test1 {

    public static void main(String[] args) {
        String str;
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("请输入新手机号: ");
            str = scanner.next();
            String regex = "^1[3-9]/d{9}$" ;
//            String phoneRegex = "^1[3-9]\\d{9}$";

            if ( str.matches(regex) ) {
                System.out.println("88888");
                break;
            }
        }

        System.out.println(str);

    }

}