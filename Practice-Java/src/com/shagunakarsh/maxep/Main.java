package com.shagunakarsh.maxep;

import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    static int MAX_COINS=1000;
    static PrintStream ps = System.out;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String []args) {

        int n = scanner.nextInt();
        int c = scanner.nextInt();

        search(1, n, c);

    }

    static void search(int min, int max, int c) {
        if(max==1) {
            println(3, 1);
            return;
        }

        int grader = -1;
        int coins = MAX_COINS;
        int cnt = MAX_COINS/c;
        int rem = MAX_COINS%c;
//        ps.println("rem="+rem);

        int mid = (min + max)/2;

        do {
            println(1, mid);
            grader = scanner.nextInt();

            if(grader == 1){
                //too high, search left
                coins -= c;
                --cnt;

                ps.println(2);
                ps.flush();

                max = mid - 1;
                mid = getBiasedMid(min, max, c);

            } else if(grader == 0) {
                // too low, search right
                coins -= 1;

                min = mid+1;
                mid = (min+max)/2;
            }

            //ps.println("min="+min + " max="+ max + " mid="+mid + " coins="+coins + " cnt="+cnt);
            if(mid==max && mid==min) break;

        } while(coins >= c && min < max && cnt > 0);

        if(cnt==0 && coins>0 && !(mid==max && mid==min)) {
            int i= min;
            do {
                println(1, i);
                grader = scanner.nextInt();
                if(grader == 1) {
                    mid =i;
                    ps.println(2);
                    ps.flush();
                    break;
                }
                ++i;
            } while (i<min+coins);
        }

//        ps.println("final grader="+grader+" mid="+ mid);

        if(mid==0) println(3, 1);
        else println(3, mid);
    }

    static int getBiasedMid(int min, int max, int c) {
        int mid = (min+max)/2;

        if(c<=50) return mid;

        if(15*min < max) {
//            ps.println("/16");
            mid = (min + max)/16;
        } else if(7*min <max) {
//            ps.println("/8");
            mid = (min + max)/8;
        } else if(3*min<max) {
//            ps.println("/4");
            mid = (min + max)/4;
        }

        return mid;
    }

    static void println(int i, int n) {
        ps.print(i);
        ps.print(" ");
        ps.print(n);
        ps.println();

        ps.flush();
    }
}
