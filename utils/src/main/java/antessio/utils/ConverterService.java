package antessio.utils;

import java.util.Map;

public interface ConverterService {

    <T> T convert(Map<String, Object> map, Class<T> cls);

    <T> Map<String, Object> convertToMap(T obj);
}
