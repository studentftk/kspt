package server;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author llama
 */
public class QueryHelper {
    
    public static String mapToSQLInsert(Map<String,Object> record, String tableName){
        StringBuilder query = new StringBuilder("INSERT INTO ")
                .append(tableName)
                .append(" (")
                .append(joinFields(record.keySet()))
                .append(") VALUES (")
                .append(joinValues(record.values()))
                .append(");");
        return query.toString();
    }
    
    public static String mapToSQLUpdate(Map<String,Object> record, String tableName){
        StringBuilder query = new StringBuilder("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(joinPairs(record))
                .append(";");
        return query.toString();
    }
    
    public static String joinFields(Collection collection){
        Iterator iterator = collection.iterator();
        StringBuilder joined = new StringBuilder();
        while (iterator.hasNext()) {
            joined.append(",").append(iterator.next());
        }
        return joined.substring(1);
    }
    
    private static String joinValues(Collection collection){
        Iterator iterator = collection.iterator();
        StringBuilder joined = new StringBuilder();
        while (iterator.hasNext()) {
            joined.append(",\"").append(iterator.next()).append("\"");
        }
        return joined.substring(1);
    }
    
    public static String joinPairs(Map<String,Object> map){
        Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
        Entry<String, Object> entry;
        StringBuilder joined = new StringBuilder();
        while (iterator.hasNext()) {
            entry = iterator.next();
            joined.append(",").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
        }
        return joined.substring(1);
    }
    
    
}
