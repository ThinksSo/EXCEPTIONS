// Обработка ошибок в программировании
// 1. Реализуйте 3 метода, чтобы в каждом из них получить разные исключения
/* 2. Посмотрите на код, и подумайте сколько разных типов исключений вы тут сможете получить? 
public static int sum2d(String[][] arr) {
    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
        for (int j = 0; j < 5; j++) {
            int val = Integer.parseInt(arr[i][j]);
            sum += val;
        }
    }
    return sum;
}
*/

/* 3. Реализуйте метод, принимающий в качестве аргументов два целочисленных массива, 
и возвращающий новый массив, каждый элемент которого равен разности элементов двух входящих массивов 
в той же ячейке. Если длины массивов не равны, необходимо как-то оповестить пользователя.
*/


import java.io.File;
import java.util.Arrays;

public class exeptType {

    public static void main(String[] args) {

        System.out.println("\n\t Task 1: Exception\n");

        DivisionByZero(5);
        NullPointerException();
        ClassCastException();
        NumberFormatException();


        //------------------------------------------------------
        System.out.println("\n\t Task 2: Exception types\n");

        String[][] text = new String[][] {
                { "0", "1", "2", "3", "4" },
                { "1", "a", "3" }
        };
        // System.out.println(sum2d(text));

        System.out.println("ArrayIndexOutOfBoundsException");
        System.out.println("NumberFormatException.forInputString");


        //------------------------------------------------------
        System.out.println("\n\t Task 3: Array subtraction\n");

        int[] array1 = { 5, 9, 1 };
        int[] array2 = { 4, 5, 6, 3 };
        System.out.println(Arrays.toString(arraySubtraction(array1, array2)));

        System.out.println();
    }


    //------------------------------------------------------
    public static void DivisionByZero(int x) {
        int y = 0;
        try {
            System.out.println(x / y);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void NullPointerException() {
        String string = null;
        try {
            System.out.println(string.length());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void ClassCastException() {
        Object object = new String("qwerty");
        try {
            File file = (File) object;
            System.out.println(file);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private static void NumberFormatException() {
        String number = "1234qwerty";
        try {
            int result = Integer.parseInt(number);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    //------------------------------------------------------
    public static int sum2d(String[][] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 5; j++) {
                int val = Integer.parseInt(arr[i][j]);
                sum += val;
            }
        }
        return sum;
    }

   
    //------------------------------------------------------
    private static boolean notEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return true;
        } else {
            return false;
        }
    }

    private static int[] arrSubtract(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            result[i] = (arr1[i] - arr2[i]);
        }
        return result;
    }

    public static int[] arraySubtraction(int[] arr1, int[] arr2) {
        if (notEqual(arr1, arr2))
            throw new RuntimeException("Arrays aren't equal\n");
        return arrSubtract(arr1, arr2);
    }
}
