package com.fortisgames.events.aggregation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.fortisgames.events.Event;

public class CompletedEventAggregator implements EventAggregator {
    private HashMap<EventKey, AggregationResult> aggregations = new HashMap<EventKey, AggregationResult>();
    private HashMap<EventKey, Set<Long>> uniqueTimestamps = new HashMap<EventKey, Set<Long>>();

    public void collectEvent(Event event) {
        EventKey eventKey = getEventKey(event);

        if (aggregations.containsKey(eventKey)) {
            uniqueTimestamps.get(eventKey).add(event.getTimestampMillis());

            AggregationResult result = aggregations.get(eventKey);
            result.setEarliestTimestamp(Math.min(result.getEarliestTimestamp(), event.getTimestampMillis()));
            result.setLatestTimestamp(Math.max(result.getLatestTimestamp(), event.getTimestampMillis()));
            result.setTotalEvents(result.getTotalEvents() + 1);
            result.setUniqueTimestamps(uniqueTimestamps.get(eventKey).size());
        } else {
            AggregationResult result = new AggregationResult(event.getGameName(), event.getEventName(), event.getTimestampMillis(), event.getTimestampMillis(), 1, 1);
            aggregations.put(eventKey, result);
            Set<Long> timestamps = new HashSet<>();
            timestamps.add(event.getTimestampMillis());
            uniqueTimestamps.put(eventKey, timestamps);
        }
    }

    public AggregationResult getAggregationResult(String gameName, String eventName) {
        return aggregations.get(new EventKey(gameName, eventName));
    }

    private EventKey getEventKey(Event event) {
        return new EventKey(event.getGameName(), event.getEventName());
    }

    record EventKey(String gameName, String eventName) {};
}
