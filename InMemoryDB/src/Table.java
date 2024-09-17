import java.util.*;
public class Table {
    private String name;
    private List<Column> columns;
    private List<Row> rows;
    private Column primaryKeyCol;
    private Set<Object> primaryKeys;
    private Index primaryKeyIndex;

    public Table(String name){
        this.name = name;
        this.columns = new ArrayList<>();
        this.rows = new ArrayList<>();
        this.primaryKeys = new HashSet<>();
        this.primaryKeyIndex = new Index();
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
            //primaryKeys.add(primaryKeyValue);
            primaryKeyIndex.addToIndex(primaryKeyValue, row);
        }
        rows.add(row);
    }

    public void deleteRow(Object primaryKeyValue){
        //rows.removeIf(row -> row.getColumnValue(primaryKeyCol).equals(primaryKeyValue));
        //primaryKeys.remove(primaryKeyValue);
        Row rowToDelete = primaryKeyIndex.getFromIndex(primaryKeyValue);
        if (rowToDelete != null) {
            rows.remove(rowToDelete);
            primaryKeyIndex.removeFromIndex(primaryKeyValue);
        }
    }

    public List<Row> selectAll(){
        return rows;
    }

    public void updateRow(Object primaryKeyValue, Column column, Object newValue) {
        Row rowToUpdate = primaryKeyIndex.getFromIndex(primaryKeyValue);
        if (rowToUpdate != null) {
            rowToUpdate.setColumnValue(column, newValue);
            primaryKeyIndex.updateIndex(primaryKeyValue, rowToUpdate);
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

    public Row selectByPrimaryKey(Object primaryKeyValue) {
        return primaryKeyIndex.getFromIndex(primaryKeyValue);
    }

    @Override
    public String toString() {
        return "Table: " + name + "\nColumns: " + columns + "\nRows: " + rows;
    }

}
