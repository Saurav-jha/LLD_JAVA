import java.util.HashMap;
import java.util.Map;

public class Database {
    private Map<String, Table> tables;
     
    public Database(){
        this.tables = new HashMap<>();
    }

    public void createTable(String tableName){
        if(!(tables.containsKey(tableName))){
            tables.put(tableName, new Table(tableName));
        }
    }

    public void dropTable(String tableName){
        tables.remove(tableName);
    }

    public Table getTable(String tableName){
        return tables.get(tableName);
    }

    public void insertIntoTable(String tableName, Row row) throws Exception {
        Table table= tables.get(tableName);
        if(table != null){
            table.insertRow(row);
        }
    }

    public void deleteFromTable(String tableName, Object primaryKeyValue){
        Table table = tables.get(tableName);
        if(table != null){
            table.deleteRow(primaryKeyValue);
        }
    }

    public void updateTable(String tableName, Object primaryKeyValue, String columnName, Object newValue){
        Table table = tables.get(tableName);
        if(table != null){
            Column column = table.getColumnByName(columnName);
            if(column != null){
                table.updateRow(primaryKeyValue, column, newValue);
            }
        }
    }

    public void selectAllFromTable(String tableName){
        Table table= tables.get(tableName);
        if(table != null){
            System.out.println(table.selectAll());
        }
    }

    public void selectByPrimaryKey(String tableName, Object primaryKeyValue) {
        Table table = tables.get(tableName);
        if (table != null) {
            Row result = table.selectByPrimaryKey(primaryKeyValue);
            System.out.println(result);
        }
    }

}
