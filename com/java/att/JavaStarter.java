package com.java.att;

import java.util.*;

public class JavaStarter {
    private List<Map<String, Object>> table = new ArrayList<>();
    private Sql sql = new Sql();
    public JavaStarter(){
    }
    public List<Map<String, Object>> execute(String request) throws Exception {
        String command = request.substring(0,7).trim().toUpperCase();
        switch (command){
            case "INSERT":
                if(request.substring(7, 14).trim().equalsIgnoreCase("VALUES")) {
                    table = sql.insert(request.substring(15).split(","),table);
                }
                break;
            case "UPDATE":
                if(request.substring(7, 14).trim().equalsIgnoreCase("VALUES")) {
                    if (!table.isEmpty()) {
                        table = sql.update(request.substring(15).trim().split("where"), table);
                    }
                }
                break;
            case "DELETE":
                if(request.substring(7,13).trim().equalsIgnoreCase("WHERE")) {
                    if (!table.isEmpty()) {
                        table = sql.delete(request.substring(14).split(" "), table);
                    }
                }
                break;
            case "SELECT":
                if(request.substring(7,13).trim().equalsIgnoreCase("WHERE")) {
                    if (!table.isEmpty()) {
                        table = sql.select(request.substring(14).trim(), table);
                    }
                }
                break;
            default:
                System.out.println("Ошибка в команде");
                break;
        }
        return table;
    }

}
