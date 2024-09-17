import java.util.*;
public class Table {
    private String name;
    private List<Column> columns;
    private List<Row> rows;
    private Column primaryKeyCol;
    private Set<Object> primaryKeys;

    public Table(String name){
        this.name = name;
        this.columns = new ArrayList<>();
        this.rows = new ArrayList<>();
        this.primaryKeys = new HashSet<>();
    }

    public void addColumn(String name, DataType type, int length, boolean isPrimaryKey){
        Column column = new Column(name, type, length, isPrimaryKey);
        columns.add(column);
        if(isPrimaryKey){
            primaryKeyCol = column;
        }
    }

    public void insertRow(Row row) throws Exception{
        if(primaryKeyCol != null){
            Object primaryKeyValue = row.getColumnValue(primaryKeyCol);
            if(primaryKeys.contains(primaryKeyValue)){
                throw new Exception("Primary key constraints voilated");
            }
            primaryKeys.add(primaryKeyValue);
        }
        rows.add(row);
    }

    public void deleteRow(Object primaryKeyValue){
        rows.removeIf(row -> row.getColumnValue(primaryKeyCol).equals(primaryKeyValue));
        primaryKeys.remove(primaryKeyValue);
    }

    public List<Row> selectAll(){
        return rows;
    }

    public void updateRow(Object primaryKeyValue, Column column, Object newValue){
        for(Row row: rows){
            if(row.getColumnValue(primaryKeyCol).equals(primaryKeyValue)){
                row.setColumnValue(column, newValue);
            }
        }
    }

    public Column getColumnByName(String columnName){
        for(Column col: columns){
            if(col.getName().equals(columnName)){
                return col;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Table: " + name + "\nColumns: " + columns + "\nRows: " + rows;
    }

}
