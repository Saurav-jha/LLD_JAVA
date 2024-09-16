import java.util.*;
enum DataType {
    INT , VARCHAR
}
public class Column {
    private String name;
    private DataType type;
    private int length;
    private boolean isPrimaryKey;

    public Column(String name, DataType type, int length, boolean isPrimaryKey){
        this.name = name;
        this.type = type;
        this.length =  length;
        this.isPrimaryKey = isPrimaryKey;
    }

    public String getName(){
        return name;
    }

    public DataType getType(){
        return type;
    }

    public boolean isPrimaryKey(){
        return isPrimaryKey;
    }

    //Constraint check
    public boolean validate(Object value, boolean isPrimaryKey, boolean isNullable, Set<Object> existingPrimaryKey ){
        //Check for null constraint
        if(value == null){
            if(!isNullable){
                return false;
            }
            else{
                return true;
            }
        }

        //Check for Primary key uniqueness
        if(isPrimaryKey){
            if(existingPrimaryKey.contains(value)){
                return false;
            }
            else{
                return true;
            }
        }

        //Check for type and length
        if(type == DataType.VARCHAR && value instanceof String){
            if(((String) value).length() > length){
                return false;
            }
        }else if(type == DataType.INT && !(value instanceof Integer)){
            return false;
        }

        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Column column = (Column) obj;
        return Objects.equals(name, column.name);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(name);
    }

    @Override
    public String toString(){
        return name + " (" + type + ")";
    }
}
