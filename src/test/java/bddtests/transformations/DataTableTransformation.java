package bddtests.transformations;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DefaultDataTableEntryTransformer;

import java.lang.reflect.Type;
import java.util.Map;

@SuppressWarnings("unused")
public class DataTableTransformation {
    private final ObjectMapper mapper = new ObjectMapper();

    @DefaultDataTableEntryTransformer
    public Object transformDataTable(Map<String, String> entry, Type toValueType) {
        JavaType constructedType = mapper.constructType(toValueType);
        return mapper.convertValue(entry, constructedType);
    }
}
