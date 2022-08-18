package watchdog.service;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import watchdog.repository.bean.WatchdogDataEntity;
import watchdog.service.bean.WatchDog;
import watchdog.service.bean.enums.WatchdogStatus;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class BaseTest {

    protected EasyRandom generator;
    private Random random;

    protected BaseTest() {
        generator = new EasyRandom();
        random = new Random();
    }

    protected WatchDog randomWatchdog() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        parameters.randomize(FieldPredicates.ofType(String.class).and(
                FieldPredicates.named("status")
                        .and(FieldPredicates.inClass(WatchDog.class))
        ), () -> randomEnum(WatchdogStatus.class));
        parameters.randomize(FieldPredicates.inClass(WatchDog.class)
                        .and(FieldPredicates.named("event")),
                this::randomMap);
        generator = new EasyRandom(parameters);
        return generator.nextObject(WatchDog.class);
    }

    private Map<String, Object> randomMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("foo", "bar");
        return map;
    }

    protected WatchdogDataEntity randomWathDogDataEntity() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        parameters.randomize(FieldPredicates.ofType(String.class).and(
                        FieldPredicates.named("status")
                                .and(FieldPredicates.inClass(WatchdogDataEntity.class))),
                () -> randomEnum(WatchdogStatus.class).name());
        parameters.randomize(FieldPredicates.inClass(WatchdogDataEntity.class).and(FieldPredicates.named("id")),
                () -> UUID.randomUUID().toString());

        generator = new EasyRandom(parameters);
        return generator.nextObject(WatchdogDataEntity.class);
    }

    protected <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }


    protected <T> T random(Class<T> clz) {
        return generator.nextObject(clz);
    }

    protected <T> List<T> randomListOf(int size, Class<T> clz) {
        return randomStreamOf(size, clz).collect(Collectors.toList());
    }

    protected <T> Stream<T> randomStreamOf(int size, Class<T> clz) {
        return generator.objects(clz, size);
    }


    protected static class EventExample {
        private String name;
        private String type;


        public EventExample(String name, String type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }
    }
}
