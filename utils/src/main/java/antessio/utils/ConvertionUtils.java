package antessio.utils;

import java.util.Arrays;

public class ConvertionUtils {
    private ConvertionUtils() {
    }
    public static <E extends Enum<E>> E convertEnum(String s, Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> e.name().equals(s))
                .map(e -> Enum.valueOf(enumClass, s))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("can't convert %s to %s".formatted(s, enumClass)));
    }
}
