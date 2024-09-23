package com.circusofvalue.events.aggregation;

public class AggregationResult {
    private String gameName;
    private String eventName;
    private long earliestTimestamp;
    private long latestTimestamp;
    private long totalEvents;
    private long uniqueTimestamps;

    public AggregationResult(String gameName, String eventName, long earliestTimestamp, long latestTimestamp, long totalEvents, long uniqueTimestamps) {
        this.eventName = eventName;
        this.gameName = gameName;
        this.earliestTimestamp = earliestTimestamp;
        this.latestTimestamp = latestTimestamp;
        this.totalEvents = totalEvents;
        this.uniqueTimestamps = uniqueTimestamps;
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

    public long getEarliestTimestamp() {
        return earliestTimestamp;
    }

    public void setEarliestTimestamp(long earliestTimestamp) {
        this.earliestTimestamp = earliestTimestamp;
    }

    public long getLatestTimestamp() {
        return latestTimestamp;
    }

    public void setUniqueTimestamps(long uniqueTimestamps) {
        this.uniqueTimestamps = uniqueTimestamps;
    }

    public long getUniqueTimestamps() {
        return uniqueTimestamps;
    }

    public void setLatestTimestamp(long latestTimestamp) {
        this.latestTimestamp = latestTimestamp;
    }

    public long getTotalEvents() {
        return totalEvents;
    }

    public void setTotalEvents(long totalEvents) {
        this.totalEvents = totalEvents;
    }
}
