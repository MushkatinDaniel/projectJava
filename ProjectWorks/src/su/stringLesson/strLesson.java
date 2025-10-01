package su.stringLesson;

import java.util.Arrays;

public class strLesson {
    public static void main(String[] args) {


        String str = ("I Love Music \nand Java"); // \n считается за один символ
        int len = str.length();  //вводить переменную не обязательно, это для примера
        System.out.println(len);
        System.out.println(str);
        System.out.println(str.length());

        String text = "    ";
        System.out.println(text.isBlank());  //методы начинающиеся на is - возвращают булевское значение
        System.out.println(text.isEmpty());
        System.out.println(str.charAt(7)); //по индексу выбирает букву строки (начинается с 0)

        char[] chars = str.toCharArray();  // преобразует строку в массив
        System.out.println(chars [8]); // вывод значения по индексу массива

        String frue = "apple, banana, orange, pineapple"; //
        String[] fruits = frue.split(",");  //не надо писать никаких regex, просто в ковычках разделитель

        System.out.println(fruits[1]);
        System.out.println(Arrays.toString(fruits));  // перевод массива строк в строку

        String s1 = "a";
        String s2 = "b";
        System.out.println(s1.join("|", s2));
    }
}
