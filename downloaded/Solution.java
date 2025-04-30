package com.javarush.test.level19.lesson10.bonus01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
оригинальный   редактированный    общий
file1:         file2:             результат:(lines)

строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка4                           REMOVED строка4
строка5        строка5            SAME строка5
строка0                           ADDED строка0
строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка5                           ADDED строка5
строка4        строка4            SAME строка4
строка5                           REMOVED строка5
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader1 = new BufferedReader(new FileReader(bufferedReader.readLine()));
        BufferedReader reader2 = new BufferedReader(new FileReader(bufferedReader.readLine()));
        bufferedReader.close();
        String s,s2;
        ArrayList<String> arr1 = new ArrayList<>();
        ArrayList<String> arr2 = new ArrayList<>();

        while ((s = reader1.readLine()) != null){
            arr1.add(s);
        }
        while ((s = reader2.readLine()) != null){
            arr2.add(s);
        }

        int i=0;
        while (arr1.size() > 0){
            s = arr1.get(i);
            if (arr2.size() == 0){
                lines.add(new LineItem(Type.REMOVED, s));
                arr1.remove(i);
            }
            else
            {
                s2 = arr2.get(i);
                if (s.equals(s2))
                {
                    lines.add(new LineItem(Type.SAME, s));
                    arr1.remove(i);
                    arr2.remove(i);
                } else
                {
                    if (s.equals(arr2.get(i + 1))){
                        lines.add(new LineItem(Type.ADDED, s2));
                        arr2.remove(i);
                    }
                    else {
                        if (arr1.get(i+1).equals(s2)){
                            lines.add(new LineItem(Type.REMOVED, s));
                            arr1.remove(i);
                        }
                    }
                }
            }
        }
        if (arr2.size() == 1) lines.add(new LineItem(Type.ADDED, arr2.get(0)));

        reader1.close();
        reader2.close();
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
