package watchdog.service.bean;

import watchdog.service.bean.enums.WatchdogStatus;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public final class WatchDogBuilder {
    private UUID id;
    private String externalId;
    private String type;
    private Date eventDate;
    private Map<String, Object> event;
    private WatchdogStatus status;

    public WatchDogBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public WatchDogBuilder withExternalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    public WatchDogBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public WatchDogBuilder withEventDate(Date eventDate) {
        this.eventDate = eventDate;
        return this;
    }

    public WatchDogBuilder withEvent(Map<String, Object> event) {
        this.event = event;
        return this;
    }

    public WatchDogBuilder withStatus(WatchdogStatus status) {
        this.status = status;
        return this;
    }

    public WatchDog build() {
        return new WatchDog(id, externalId, type, eventDate, event, status);
    }
}
