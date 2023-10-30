package com.java.att;
import java.util.*;

public class Sql {

    public Sql() {
    }

    public List<Map<String, Object>> insert(String[] request,List<Map<String, Object>> table) throws Exception {
        table.add(createRow(request));
        return table;
    }

    public List<Map<String, Object>> update(String[] request, List<Map<String, Object>> table) throws Exception {
        Map<String, Object> updateData = createRow(request[0].trim().replace(" ", "").split(","));
        Map<String, Object> whereRow = createRow(request[1].trim().replace(" ", "").split(","));

        int index;

        for (Map<String, Object> row : table) {

            if (whereRow.containsKey("id")) {
                Object value = row.get("id");
                if (value != null) {
                    if (value.equals(whereRow.get("id"))) {
                        index = table.indexOf(row);
                        replaceRow(table,index,updateData);
                    }
                }
            }

            if (whereRow.containsKey("lastname")) {
                Object value = row.get("lastname");
                if (value != null) {
                    if (value.equals(whereRow.get("lastname"))) {
                        index = table.indexOf(row);
                        replaceRow(table,index,updateData);
                    }
                }
            }

            if (whereRow.containsKey("cost")) {
                Object value = row.get("cost");
                if (value != null) {
                    if (value.equals(whereRow.get("cost"))) {
                        index = table.indexOf(row);
                        replaceRow(table,index,updateData);
                    }
                }
            }

            if (whereRow.containsKey("age")) {
                Object value = row.get("age");
                if (value != null) {
                    if (value.equals(whereRow.get("age"))) {
                        index = table.indexOf(row);
                        replaceRow(table,index,updateData);
                    }
                }
            }
            if (whereRow.containsKey("active")) {
                Object value = row.get("active");
                if (value != null) {
                    if (value.equals(whereRow.get("active"))) {
                        index = table.indexOf(row);
                        replaceRow(table,index,updateData);
                    }
                }
            }
        }
        return table;
    }

    public List<Map<String, Object>> delete(String[] request, List<Map<String, Object>> table) throws Exception {
        Map<String, Object> whereRow = createRow(request[0].split(","));
        List<Map<String, Object>> toRemove = new ArrayList<>();

        for (Map<String, Object> row : table) {

            if (whereRow.containsKey("id")) {
                Object value = row.get("id");
                if (value != null) {
                    if (value.equals(whereRow.get("id"))) {
                        toRemove.add(row);
                    }
                }
            }

            if (whereRow.containsKey("lastname")) {

                Object value = row.get("lastname");
                if (value != null) {
                    if (value.equals(whereRow.get("lastname"))) {
                        toRemove.add(row);
                    }
                }
            }

            if (whereRow.containsKey("cost")) {

                Object value = row.get("cost");
                if (value != null) {
                    if (value.equals(whereRow.get("cost"))) {
                        toRemove.add(row);
                    }
                }
            }


            if (whereRow.containsKey("age")) {

                Object value = row.get("age");
                if (value != null) {
                    if (value.equals(whereRow.get("age"))) {
                        toRemove.add(row);
                    }
                }
            }

            if (whereRow.containsKey("active")) {

                Object value = row.get("active");
                if (value != null) {
                    if (value.equals(whereRow.get("active"))) {
                        toRemove.add(row);
                    }
                }

            }
        }

        table.removeAll(toRemove);

        return table;
    }

    public List<Map<String, Object>> select(String request,List<Map<String, Object>> table) {
        String[] condition = getCondition(request);
        System.out.println(Arrays.toString(condition));
        Map<String, Object> leftCondition = new HashMap<>();
        Map<String, Object> rightCondition = new HashMap<>();

        if(condition != null){
            if(condition[0].contains("=")){
                leftCondition = createRow(condition[0],"=");
            }
            else if(condition[1].contains("=")) {
                rightCondition = createRow(condition[1],"=");
            }

            if(condition[0].contains("!=")){
                leftCondition = createRow(condition[0],"!=");
            }
            else if(condition[1].contains("!=")) {
                rightCondition = createRow(condition[1],"!=");
            }

            if(condition[0].contains("like")){
                leftCondition = createRow(condition[0],"like");
            }
            else if(condition[1].contains("like")) {
                rightCondition = createRow(condition[1],"like");
            }

            if(condition[0].contains("ilike")){
                leftCondition = createRow(condition[0],"ilike");
            }
            else if(condition[1].contains("ilike")) {
                rightCondition = createRow(condition[1],"ilike");
            }

            if(condition[0].contains(">=")){
                leftCondition = createRow(condition[0],">=");
            }
            else if(condition[1].contains(">=")) {
                rightCondition = createRow(condition[1],">=");
            }

            if(condition[0].contains("<=")){
                leftCondition = createRow(condition[0],"<=");
            }
            else if(condition[1].contains("<=")) {
                rightCondition = createRow(condition[1],"<=");
            }

            if(condition[0].contains("<")){
                leftCondition = createRow(condition[0],"<");
            }
            else if(condition[1].contains("<")) {
                rightCondition = createRow(condition[1],"<");
            }

            if(condition[0].contains(">")){
                leftCondition = createRow(condition[0],">");
            }
            else if(condition[1].contains(">")) {
                rightCondition = createRow(condition[1],">");
            }
        }
        else {

        }
        System.out.println(leftCondition);
        System.out.println(rightCondition);




        return table;
    }

    private Map<String, Object> createRow(String[] request) {
        Map<String, Object> row = new HashMap<>();
        String index = "";
        Object value = null;
        String symbol ="=";
        for (String line : request) {
            index = getValues(line,symbol)[0].replaceAll(" \\s+", "").replaceAll("[',‘]", "").toLowerCase().trim();
            switch (index) {
                case "id":
                    value = Long.valueOf(getValues(line,symbol)[1].trim());
                    break;

                case "lastname":
                    value = getValues(line,symbol)[1].trim().replaceAll("'", "");
                    break;

                case "cost":
                    value = Double.valueOf(getValues(line,symbol)[1].trim());
                    break;

                case "age":
                    value = Long.valueOf(getValues(line,symbol)[1].trim());
                    break;

                case "active":
                    value = Boolean.valueOf(getValues(line,symbol)[1].trim());
                    break;
            }
            row.put(index, value);
        }
        return row;
    }
    private Map<String, Object> createRow(String request, String symbol) {
        Map<String, Object> row = new HashMap<>();
        String index = "";
        Object value = null;
            index = getValues(request, symbol)[0].replaceAll(" \\s+", "").replaceAll("[^\\w]", "").toLowerCase().trim();

            switch (index) {
                case "id":
                    value = Long.valueOf(getValues(request, symbol)[1].trim().replaceAll("=",""));
                    break;

                case "lastname":
                    value = getValues(request, symbol)[1].trim().replaceAll("'","");
                    break;

                case "cost":
                    value = Double.valueOf(getValues(request, symbol)[1].trim().replaceAll("=",""));
                    break;

                case "age":
                    value = Long.valueOf(getValues(request, symbol)[1].trim().replaceAll("=",""));
                    break;

                case "active":
                    value = Boolean.valueOf(getValues(request, symbol)[1].trim().replaceAll("'",""));
                    break;
        }
        row.put(index, value);
        return row;
    }
    private String[] getValues(String request, String symbol) {
        if(symbol.equalsIgnoreCase("=")) {
            return request.trim().split("=");
        }
        if(symbol.equalsIgnoreCase("!=")){
            return request.trim().split("!=");
        }
        if(symbol.equalsIgnoreCase("like")){
            return request.trim().split("like");
        }
        if(symbol.equalsIgnoreCase("ilike")){
            return request.trim().split("ilike");
        }
        if(symbol.equalsIgnoreCase(">=")){
            return request.trim().split(">=");
        }
        if(symbol.equalsIgnoreCase("<=")){
            return request.trim().split("<=");
        }
        if(symbol.equalsIgnoreCase("<")){
            return request.trim().split("<");
        }
        if(symbol.equalsIgnoreCase(">")){
            return request.trim().split(">");
        }
        else{
            return null;
        }
    }

    private String[] getCondition(String request){

        if(request.contains(" and ")){
            return request.split("and");
        }
        if(request.contains(" or ")){
            return  request.split("or");
        }
        if(request.contains("AND")){
            return request.split("AND");
        }
        if(request.contains(" OR ")){
            return request.split("OR");
        }
        else {
            return null;
        }
    }


    private void replaceRow(List<Map<String, Object>> table,int index, Map<String, Object> updateData){
        Map<String, Object> newRow = new HashMap<>();

        for (int i = 0; i < table.size(); i++) {

            if (updateData.containsKey("age")) {
                if (table.get(index).containsKey("age")) {
                    newRow.put("age", updateData.get("age"));
                }
            }
            else {
                newRow.put("age", table.get(index).get("age"));
            }

            if (updateData.containsKey("id")) {
                if (table.get(index).containsKey("id")) {
                    newRow.put("id", updateData.get("id"));
                }
            }
            else {
                newRow.put("id", table.get(index).get("id"));
            }

            if (updateData.containsKey("active")) {
                if (table.get(index).containsKey("active")) {
                    newRow.put("active", updateData.get("active"));
                }
            }
            else {
                newRow.put("active", table.get(index).get("active"));
            }

            if (updateData.containsKey("lastname")) {
                if (table.get(index).containsKey("lastname")) {
                    newRow.put("lastname", updateData.get("lastname"));
                }
            }
            else {
                newRow.put("lastname", table.get(index).get("lastname"));
            }

            if (updateData.containsKey("cost")) {
                if (table.get(index).containsKey("cost")) {
                    newRow.put("cost", updateData.get("cost"));
                }
            }
            else {
                newRow.put("cost", table.get(index).get("cost"));
            }
        }
        table.set(index,newRow);
    }
}
