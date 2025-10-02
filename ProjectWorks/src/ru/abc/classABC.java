package ru.abc;
import java.util.Scanner;
public class classABC {
        public static void main(String[] args) {
            Scanner s = new Scanner (System.in);
            int a = s.nextInt();
            int b = s.nextInt();
            int c = s.nextInt();
            boolean ab = (a == b);
            boolean bc = (b == c);
            boolean ac = (a == c);

            if (ab && bc) {
                //if (ac) {
                    System.out.println(a + " " + b + " " + c);
               // } else {
               //     System.out.println(a + " " + b);

            }
            else if (ab) {
                System.out.println(a + " " + b);
            }
                else if (ac) {
                    System.out.println(a + " " + c);
                }
            else if (bc) {
                System.out.println (b + " " + c);
            }



            }
        }//напишите тут ваш код



