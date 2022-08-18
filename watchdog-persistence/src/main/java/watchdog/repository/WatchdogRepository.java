package watchdog.repository;

import watchdog.repository.bean.WatchdogDataEntity;

import java.util.List;

public interface WatchdogRepository {

    WatchdogDataEntity insert(WatchdogDataEntity watchdogDataEntity);

    List<WatchdogDataEntity> getByExternalId(String externalId, Integer limit, Long startingFrom);

    List<WatchdogDataEntity> getByStatusPending(Integer limit, Long startingFrom);


}
