package antessio.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JacksonConverterService implements ConverterService {
    private ObjectMapper objectMapper;

    public JacksonConverterService() {
        this.objectMapper = new ObjectMapper();
    }

    public <T> T convert(Map<String, Object> map, Class<T> cls) {
        return objectMapper.convertValue(map, cls);
    }

    public <T> Map<String, Object> convertToMap(T obj) {
        return objectMapper.convertValue(obj, new TypeReference<Map<String, Object>>() {
        });
    }
}
