package devv.rbfz.ember.utilities;

import java.util.*;

public class MapUtils {
    public static <K> Map<K, Integer> sort(Map<K, Integer> map) {
        // Convert map entries to a list
        ArrayList<Map.Entry<K, Integer>> entryList = new ArrayList<>(map.entrySet());

        // Sort the list manually based on values in descending order
        entryList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Create a new LinkedHashMap to maintain the sorted order
        Map<K, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<K, Integer> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
