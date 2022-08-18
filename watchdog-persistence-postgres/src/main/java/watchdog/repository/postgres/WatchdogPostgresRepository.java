package watchdog.repository.postgres;

import watchdog.repository.WatchdogRepository;
import watchdog.repository.bean.WatchdogDataEntity;

import java.util.List;

public class WatchdogPostgresRepository implements WatchdogRepository {
    @Override
    public WatchdogDataEntity insert(WatchdogDataEntity watchdogDataEntity) {
        return null;
    }

    @Override
    public List<WatchdogDataEntity> getByExternalId(String externalId, Integer limit, Long startingFrom) {
        return null;
    }

    @Override
    public List<WatchdogDataEntity> getByStatusPending(Integer limit, Long startingFrom) {
        return null;
    }
}
