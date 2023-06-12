// Исключения и их обработка
/*  1. Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float), 
и возвращает введенное значение. Ввод текста вместо числа не должно приводить к падению приложения, 
вместо этого, необходимо повторно запросить у пользователя ввод данных.
*/
/* 2. Если необходимо, исправьте данный код 
(задание 2 https://docs.google.com/document/d/17EaA1lDxzD5YigQ5OAal60fOFKVoCbEJqooB9XfhT7w/edit)
 */
/* 3. Дан следующий код, исправьте его там, где требуется 
(задание 3 https://docs.google.com/document/d/17EaA1lDxzD5YigQ5OAal60fOFKVoCbEJqooB9XfhT7w/edit)
 */
/* 4. Разработайте программу, которая выбросит Exception, когда пользователь вводит пустую строку. 
Пользователю должно показаться сообщение, что пустые строки вводить нельзя.
*/

import java.util.Scanner;

public class ioException {

    public static void main(String[] args) {

        System.out.println("\n\t Task 1: Entering a float number\n");
        System.out.println("Entered number: " + floatInput());

        // ------------------------------------------------------
        System.out.println("\n\t Task 2: Exception when dividing by zero\n");
        int[] intArray = { 1, 2, 3, 4, 5, 6, 7 };
        divByZero(intArray);

        // ------------------------------------------------------
        System.out.println("\n\t Task 4: Empty string error\n");
        noEmpty();

        System.out.println();

    }

    // ------------------------------------------------------
    private static float floatInput() {
        Scanner scan = new Scanner(System.in);
        try {
            System.out.print("Please enter float number: ");
            return Float.parseFloat(scan.nextLine());
        } catch (Exception e) {
            System.out.println("The entered data isn't float number\n");
            return floatInput();
        }
    }

    // ------------------------------------------------------
    public static void divByZero(int[] intArray) {
        int d = 0;
        try {
            double catchedRes1 = intArray[8] / d;
            System.out.println("catchedRes1 = " + catchedRes1);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Array length is shorter than specified\n");
        } catch (ArithmeticException e) {
            System.out.println("Catching exception: " + e);
        }
    }

    // ------------------------------------------------------
    private static void noEmpty() {
        System.out.println("Please enter text: ");
        try (Scanner scanner = new Scanner(System.in)) {
            String text = scanner.nextLine();
            if (text.isEmpty()) {
                System.out.println("Attention: Empty string entered\n");
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }
}
