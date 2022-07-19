package com.mengxuegu.member.base;

import java.util.Scanner;

public class CompetitionTestProgram {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num1 = 0;
        int num2 = 0;
        try {
            num1 = input.nextInt();
            num2 = input.nextInt();
        } catch (Exception e) {

        }
        int numAdd = num1 + num2;
        int numDesc = num1 - num2;
        System.out.println(num1 + "+" + num2 + "=" + numAdd);
        System.out.println(num1 + "-" + num2 + "=" + numDesc);
        input.close();
    }
}
