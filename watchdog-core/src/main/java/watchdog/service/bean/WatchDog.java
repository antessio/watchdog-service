package watchdog.service.bean;

import watchdog.service.bean.enums.WatchdogStatus;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class WatchDog {
    private UUID id;
    private String externalId;
    private String type;
    private Date eventDate;
    private Map<String, Object> event;

    private WatchdogStatus status;

    public WatchDog(UUID id, String externalId, String type, Date eventDate, Map<String, Object> event, WatchdogStatus status) {
        this.id = id;
        this.externalId = externalId;
        this.type = type;
        this.eventDate = eventDate;
        this.event = event;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public String getExternalId() {
        return externalId;
    }

    public String getType() {
        return type;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public Map<String, Object> getEvent() {
        return event;
    }

    public WatchdogStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "WatchDog{" +
                "id=" + id +
                ", externalId='" + externalId + '\'' +
                ", type='" + type + '\'' +
                ", eventDate=" + eventDate +
                ", event=" + event +
                ", status=" + status +
                '}';
    }
}
