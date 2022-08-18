package watchdog.service;

import watchdog.service.bean.WatchDog;

import java.util.stream.Stream;

public interface WatchdogService {

    WatchDog create(WatchDog watchDog);

    Stream<WatchDog> getByExternalId(String externalId);

    Stream<WatchDog> processPending();
}
