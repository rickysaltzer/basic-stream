package com.circusofvalue.events;

public class Event {
    private String gameName;
    private String eventName;
    private long timestampMillis;

    public Event(String gameName, String eventName, long timestampMillis) {
        this.gameName = gameName;
        this.eventName = eventName;
        this.timestampMillis = timestampMillis;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public long getTimestampMillis() {
        return timestampMillis;
    }

    public void setTimestampMillis(long timestampMillis) {
        this.timestampMillis = timestampMillis;
    }
}
