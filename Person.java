// Продвинутая работа с исключениями в Java
/* Напишите приложение, которое будет запрашивать у пользователя следующие данные, 
разделенные пробелом: Фамилия Имя Отчество датарождения номертелефона пол
Форматы данных:
фамилия, имя, отчество - строки
дата рождения - строка формата dd.mm.yyyy
номер телефона - целое беззнаковое число без форматирования
пол - символ латиницей f или m.
*/
/* Приложение должно проверить введенные данные по количеству. 
Если количество не совпадает с требуемым, вернуть исключение, 
обработать его и показать пользователю сообщение, что он ввел меньше или больше данных, чем требуется.*/
/*Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. 
Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. 
Можно использовать встроенные типы java и создать свои. 
Исключение должно быть корректно обработано, пользователю выведено сообщение 
с информацией, что именно неверно. */
/* Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, 
в него в одну строку должны записаться полученные данные, вида
<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
Не забудьте закрыть соединение с файлом. 
При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, 
пользователь должен увидеть стектрейс ошибки.
*/

// Ivanov Ivan Ivanych 12.01.1989 12345678 m 
// Petrov Petr Petrovich 09.11.1997 87654321 m

import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Person {

    private String familyName;
    private String firstName;
    private String patronymic;
    private String datefBirth;
    private int phoneNum;
    private char gender;

    public Person(String familyName, String firstName, String patronymic, String datefBirth,
            int phoneNum, char gender) {
        this.familyName = familyName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.datefBirth = datefBirth;
        this.phoneNum = phoneNum;
        this.gender = gender;
    }

    public static void main(String[] args) {

        List<String[]> allPersons = new ArrayList<>();
        String[] anyPersonData;

        while (true) {
            anyPersonData = parseToArray(newData());
            allPersons.add(anyPersonData);

            if (!checkData(anyPersonData)) {
                Person newPerson = new Person(
                        anyPersonData[0],
                        anyPersonData[1],
                        anyPersonData[2],
                        toSimpleDateFormat(anyPersonData[3]),
                        (int) toInt(anyPersonData[4]),
                        (char) toChar(anyPersonData[5]));

                System.out.println(newPerson.toString());

                try {
                    String filePath = anyPersonData[0] + ".txt";
                    saveFile(anyPersonData, filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String newData() {
        System.out.println("\nPlease enter Person data space-separated\n" +
                "familyName firstName patronymicName datefBirth(dd.mm.yyyy)" +
                "phoneNumber(7-11numbers) gender(f/m): ");
        Scanner scanner = new Scanner(System.in);
        /*
         * try (Scanner scanner = new Scanner(System.in)) {
         * // String data = scanner.nextLine();
         * // if (!data.isEmpty()) {
         * // return data;
         * // } else {
         * // System.out.println("Attention: Empty string entered\n");
         * // }
         * // } catch (Exception e) {
         * // System.out.println("Error" + e);
         * // }
         */
        String data = scanner.nextLine();
        if (data.equalsIgnoreCase("stop") || data.equalsIgnoreCase("end")
                || data.equalsIgnoreCase("exit"))
            throw new RuntimeException("Stop by user\n");
        return data;
    }

    private static String[] parseToArray(String data) {
        String[] personData;
        try {
            personData = data.split(" ");
            return personData;
        } catch (Exception e) {
            System.out.println("Parsing error" + e);
        }
        return null;
    }


    
    private static boolean checkData(String[] personData) {
        if (personData.length < 6)
            throw new RuntimeException("Not enough data\n");
        if (personData.length > 6)
            throw new RuntimeException("Extra data\n");

        Date date = new Date();
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            date = df.parse(personData[3]);
            if (!personData[3].equals(df.format(date))) {
                date = null;
                System.out.println("date " + df.format(date));
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        if (date == null) {
            System.out.println("Invalid date format");
        }

        try {
            Integer.parseInt(personData[4]);
        } catch (Exception e) {
            System.out.println("Error: Invalid phone number format" + e);
        }

        if (personData[4].length() < 7 || personData[4].length() > 11)
            throw new RuntimeException("Wrong number of digits in phone number\n");

        try {
            personData[5].charAt(0);
        } catch (Exception e) {
            System.out.println("Error: Invalid gender format " + e);
        }

        if (!personData[5].equalsIgnoreCase("m") || personData[5].equalsIgnoreCase("f"))
            throw new RuntimeException("Invalid gender\n");
        return false;
    }


    public static String toSimpleDateFormat(String date) {
        String newDate;
        try {
            newDate = new SimpleDateFormat("dd.MM.yyyy").format(new SimpleDateFormat("dd.MM.yyyy").parse(date));
            return newDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Object toInt(String num) {
        Integer newNum = Integer.parseInt(num);
        return newNum;
    }

    public static Object toChar(String gender) {
        char newChar = gender.charAt(0);
        return newChar;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDatefBirth() {
        return datefBirth;
    }

    public void setDatefBirth(String datefBirth) {
        this.datefBirth = datefBirth;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

   
    @Override
    public String toString() {
        return "\nfull name:\t" + familyName + " " + firstName + " " + patronymic
                + "\ndate of Birth:\t" + datefBirth + "\nphone:\t\t" + phoneNum + "\nsex:\t\t" + gender + "\n";
    }


    static void saveFile(String[] pd, String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : pd) {
            stringBuilder.append("<" + s + ">");
        }
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(stringBuilder.toString() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
