package service.utils;

import java.util.HashMap;

/**
 * @author Zarrow
 */
public class Util {
    public static String layerParameterToString(HashMap<String,String> map){
        StringBuilder stringBuilder = new StringBuilder();
        for (String key: map.keySet()){
            if (stringBuilder.length() != 0){
                stringBuilder.append(", ");
            }
            String value = map.get(key);
            if ("kernel_size".equals(key) || "pool_size".equals(key)){
                value = "(" + value+")";
            }
            else if ("activation".equals(key)){
                value = "\"" + value +"\"";
            }
            stringBuilder.append(key).append("=").append(value);
        }
        return stringBuilder.toString();
    }


}
