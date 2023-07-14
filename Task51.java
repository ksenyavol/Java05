package Lesson5;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Task51 {
    public static void main(String[] args) {
        
        // Реализуйте структуру телефонной книги с помощью HashMap.
        // Программа также должна учитывать, что во входной структуре
        // будут повторяющиеся имена с разными телефонами,
        // их необходимо считать, как одного человека с разными телефонами.
        // Вывод должен быть отсортирован по убыванию числа телефонов.
        // Пример:
        // addNumber("Petrov" "9999876767");
        // addNumber("Petrov", "9877877766");
        // addNumber("Ivanov", "9876545433");
        // addNumber("Petrov", "9976765566");
        // addNumber("Ivanov", "9932343222");
        // addNumber("Davidov", "9999767655")

        Map<String, ArrayList<String>> phones = new HashMap<>();
        Map<String, Integer> counts = new HashMap<>();
        ArrayList<String> names = new ArrayList<>();

        String name_phone;
        String name;
        String phone;
        String[] name_phone_arr;

        try (BufferedReader file = new BufferedReader(new FileReader("phone_book.data"))) {
            name_phone = file.readLine();
            while (name_phone != null) {
                name_phone_arr = name_phone.split(" ");
                name = name_phone_arr[0];
                phone = name_phone_arr[1];
                if (phones.containsKey(name)) {
                    phones.get(name).add(phone);
                    counts.put(name, counts.get(name) + 1);
                }
                else {
                    phones.put(name, new ArrayList<>());
                    phones.get(name).add(phone);
                    counts.put(name, 1);
                    names.add(name);
                }
                name_phone = file.readLine();
            }
        }
        catch (IOException exc) {
            System.out.println(exc.getMessage());
        }

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String name1, String name2) {
                return counts.get(name2) - counts.get(name1);
            }
        });

        for (String nname: names) {
            for (String phone_number: phones.get(nname)) {
                System.out.println(nname + " " + phone_number);
            }
        }
    }
}

