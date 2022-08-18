package watchdog.service.converter;

import antessio.utils.ConverterService;
import antessio.utils.ConvertionUtils;
import watchdog.repository.bean.WatchdogDataEntity;
import watchdog.service.bean.WatchDog;
import watchdog.service.bean.WatchDogBuilder;
import watchdog.service.bean.enums.WatchdogStatus;

import java.util.Optional;
import java.util.UUID;

public class WatchdogConverter {

    private ConverterService converterService;

    public WatchdogConverter(ConverterService converterService) {
        this.converterService = converterService;
    }

    public WatchDog toBean(WatchdogDataEntity watchdogDataEntity) {
        return new WatchDogBuilder()
                .withEventDate(watchdogDataEntity.getEventDate())
                .withExternalId(watchdogDataEntity.getExternalId())
                .withStatus(convertStatusEnum(watchdogDataEntity))
                .withId(UUID.fromString(watchdogDataEntity.getId()))
                .withEvent(watchdogDataEntity.getEvent())
                .withType(watchdogDataEntity.getType())
                .build();
    }


    private WatchdogStatus convertStatusEnum(WatchdogDataEntity watchdogDataEntity) {
        return Optional.ofNullable(watchdogDataEntity.getStatus())
                .map(s -> ConvertionUtils.convertEnum(s, WatchdogStatus.class))
                .orElse(null);
    }


    public WatchdogDataEntity toEntity(WatchDog watchDog) {
        return new WatchdogDataEntity(
                watchDog.getId().toString(),
                watchDog.getExternalId(),
                watchDog.getType(),
                watchDog.getEventDate(),
                watchDog.getEvent(),
                watchDog.getStatus().name(),
                null, null, null, null
        );
    }
}
