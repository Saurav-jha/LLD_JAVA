import java.util.HashMap;
import java.util.Map;
public class Row {
    private Map<Column, Object> columnData;

    public Row(){
        this.columnData = new HashMap<>();
    }

    public void setColumnValue(Column column, Object value){
        this.columnData.put(column, value);
    }

    public Object getColumnValue(Column column){
        return this.columnData.get(column);
    }

    @Override
    public String toString(){
        return columnData.toString();
    }
}
