package pbt;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

class LetterCounter {
    ImmutableMap<Character, Integer> countOccurrences(String sentence) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c: sentence.toCharArray()) {
//            map.put(c, map.getOrDefault(c, 0) + 1);
            map.put(c, map.getOrDefault(c, 0));
        }
        return ImmutableMap.copyOf(map);
    }
}
