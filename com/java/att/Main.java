package com.java.att;
import java.util.List;
import java.util.Map;
public class Main {
    public static void main(String[] args) {
        JavaStarter command = new JavaStarter();
        try {
            //Вставка строки в коллекцию
            List<Map<String, Object>> result1 = command.execute("INSERT VALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true");
            List<Map<String, Object>> result2 = command.execute("INSERT VALUES 'lastName' = 'Поляков' , 'id'=4, 'age'=30, 'active'=false");
            List<Map<String, Object>> result3 = command.execute("INSERT VALUES 'lastName' = 'Петров' , 'id'=3, 'age'=30, 'active'=false");
            //Обновление строки в коллекции
            List<Map<String,Object>> result4 = command.execute("UPDATE VALUES 'active‘=false, 'cost'=10.1 where 'id'=3");
            //Удаление строки в коллекции
            List<Map<String,Object>> result5 = command.execute("DELETE WHERE 'age'=30");


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
