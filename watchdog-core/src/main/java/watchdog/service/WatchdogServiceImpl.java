package watchdog.service;

import watchdog.service.bean.WatchDog;

import java.util.stream.Stream;

public class WatchdogServiceImpl implements WatchdogService {


    private WatchdogLoadService watchdogLoadService;

    private WatchdogProcessor watchDogProcessor;

    public WatchdogServiceImpl(WatchdogLoadService watchdogLoadService,
                               WatchdogProcessor watchDogProcessor) {
        this.watchdogLoadService = watchdogLoadService;
        this.watchDogProcessor = watchDogProcessor;
    }

    @Override
    public WatchDog create(WatchDog watchDog) {
        return null;
    }

    @Override
    public Stream<WatchDog> getByExternalId(String externalId) {
        return watchdogLoadService.getByExternalId(externalId);
    }

    @Override
    public Stream<WatchDog> processPending() {

        return watchdogLoadService
                .getPending()
                .map(watchDogProcessor::process);
    }


}
