package watchdog.service;

import watchdog.repository.WatchdogRepository;
import watchdog.repository.exception.PersistenceException;
import watchdog.service.bean.WatchDog;
import watchdog.service.converter.WatchdogConverter;
import watchdog.service.exception.WatchdogException;

import java.util.Collection;
import java.util.stream.Stream;

class WatchdogCrudService {


    private WatchdogRepository watchdogRepository;
    private WatchdogConverter watchdogConverter;

    WatchdogCrudService(WatchdogRepository watchdogRepository,
                               WatchdogConverter watchdogConverter) {
        this.watchdogRepository = watchdogRepository;
        this.watchdogConverter = watchdogConverter;
    }

    public WatchDog insert(WatchDog watchDog) {
        try {
            return watchdogConverter.toBean(
                    watchdogRepository.insert(
                            watchdogConverter.toEntity(watchDog))
            );
        } catch (PersistenceException e) {
            throw new WatchdogException("unable to insert data", e);
        }
    }
}
