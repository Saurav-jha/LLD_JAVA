import java.util.HashMap;
import java.util.Map;
public class Index {
    private Map<Object, Row> indexMap;

    public Index(){
        this.indexMap = new HashMap<>();
    }

    public void addToIndex(Object key, Row row){
        this.indexMap.put(key, row);
    }

    public Row getFromIndex(Object key){
        return this.indexMap.get(key);
    }

    public void removeFromIndex(Object key){
        this.indexMap.remove(key);
    }

    public boolean containsKey(Object key){
        return this.indexMap.containsKey(key);
    }

    public void updateIndex(Object key, Row row){
        this.indexMap.put(key, row);
    }

}
