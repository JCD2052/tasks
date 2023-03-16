package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class Convertor {

    public static <O> HashMap<String, ?> convertToHashMap(O object) {
        return new ObjectMapper().convertValue(object, HashMap.class);
    }
}
