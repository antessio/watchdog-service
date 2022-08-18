package watchdog.service;

import antessio.utils.JacksonConverterService;
import watchdog.repository.WatchdogRepository;
import watchdog.service.bean.WatchDog;
import watchdog.service.converter.WatchdogConverter;

import java.util.stream.Stream;

public class WatchdogServiceImpl implements WatchdogService {


    private WatchdogLoadService watchdogLoadService;

    private WatchdogProcessor watchDogProcessor;

    public WatchdogServiceImpl(WatchdogRepository watchdogRepository) {
        this.watchdogLoadService = new WatchdogLoadService(watchdogRepository,
                new WatchdogConverter(new JacksonConverterService()));
        this.watchDogProcessor = new WatchdogProcessor();
    }

    WatchdogServiceImpl(WatchdogLoadService watchdogLoadService, WatchdogProcessor watchDogProcessor){
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
