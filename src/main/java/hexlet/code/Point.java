package hexlet.code;

public final class Point {

    private final String key;
    private final Object previousValue;
    private final Object lastValue;
    private final Enum<Status> status;

    public String getKey() {
        return key;
    }

    public Object getPreviousValue() {
        return previousValue;
    }

    public Object getLastValue() {
        return lastValue;
    }

    public Enum<Status> getStatus() {
        return status;
    }

    public Point(String keyName, Object beforeValue, Object afterValue, Enum<Status> state) {
        this.key = keyName;
        this.previousValue = beforeValue;
        this.lastValue = afterValue;
        this.status = state;
    }
}
