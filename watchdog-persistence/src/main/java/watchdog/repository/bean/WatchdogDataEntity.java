package watchdog.repository.bean;


import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class WatchdogDataEntity {

    private String id;
    private String externalId;
    private String type;
    private Date eventDate;
    private Map<String, Object> event;
    private String status;
    private Long statusOrdering;
    private Boolean lock;
    private Date lockUntil;
    private Long ordering;

    public WatchdogDataEntity(String id, String externalId, String type, Date eventDate, Map<String, Object> event, String status, Long statusOrdering, Boolean lock, Date lockUntil, Long ordering) {
        this.id = id;
        this.externalId = externalId;
        this.type = type;
        this.eventDate = eventDate;
        this.event = event;
        this.status = status;
        this.statusOrdering = statusOrdering;
        this.lock = lock;
        this.lockUntil = lockUntil;
        this.ordering = ordering;
    }

    public String getId() {
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

    public String getStatus() {
        return status;
    }

    public Long getStatusOrdering() {
        return statusOrdering;
    }

    public Boolean getLock() {
        return lock;
    }

    public Date getLockUntil() {
        return lockUntil;
    }

    public Long getOrdering() {
        return ordering;
    }

    @Override
    public String toString() {
        return "WatchdogDataEntity{" +
                "id='" + id + '\'' +
                ", externalId='" + externalId + '\'' +
                ", type='" + type + '\'' +
                ", eventDate=" + eventDate +
                ", event=" + event +
                ", status='" + status + '\'' +
                ", statusOrdering=" + statusOrdering +
                ", lock=" + lock +
                ", lockUntil=" + lockUntil +
                ", ordering=" + ordering +
                '}';
    }
}
