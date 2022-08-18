package watchdog.service;

import watchdog.repository.WatchdogRepository;
import watchdog.service.bean.WatchDog;
import watchdog.service.converter.WatchdogConverter;

import java.util.Collection;
import java.util.stream.Stream;

class WatchdogLoadService {

    private WatchdogRepository watchdogRepository;
    private WatchdogConverter watchdogConverter;

    public WatchdogLoadService(WatchdogRepository watchdogRepository, WatchdogConverter watchdogConverter) {
        this.watchdogRepository = watchdogRepository;
        this.watchdogConverter = watchdogConverter;
    }

    public Stream<WatchDog> getByExternalId(String externalId) {
        final int batchSize = 5;
        return Stream.iterate(watchdogRepository.getByExternalId(externalId, batchSize, null),
                        l -> !l.isEmpty(),
                        results -> {
                            Long nextStartingAfter = results.get(results.size() - 1).getOrdering();
                            return watchdogRepository.getByExternalId(externalId, batchSize, nextStartingAfter);
                        })
                .flatMap(Collection::stream)
                .map(w -> watchdogConverter.toBean(w));

    }

    public Stream<WatchDog> getPending() {
        final int batchSize = 5;
        return Stream.iterate(watchdogRepository.getByStatusPending(batchSize, null),
                        l -> !l.isEmpty(),
                        results -> {
                            Long nextStartingAfter = results.get(results.size() - 1).getOrdering();
                            return watchdogRepository.getByStatusPending(batchSize, nextStartingAfter);
                        })
                .flatMap(Collection::stream)
                .map(w -> watchdogConverter.toBean(w));

    }
}
