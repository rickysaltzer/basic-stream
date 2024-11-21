package com.circusofvalue.events.aggregation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import com.circusofvalue.events.Event;

public class InMemoryEventAggregator implements EventAggregator{
    /**
     * Collect an incoming event
     */
    HashMap<String, HashSet<Long>> uniguqeTimeStamps = new HashMap<String, HashSet<Long>>();

    HashMap<String, AggregationResult> results = new HashMap<String, AggregationResult>();

    private String generateKey(String gameName, String eventName) {
        // assume ":" is not used anywhere in the names
        return String.format("%s:%s", gameName, eventName);
    }

    public void collectEvent(Event event) {
        var key =  generateKey(event.getGameName(), event.getEventName());
        var eventTime = event.getTimestampMillis();
        var record = results.get(key);

        if (record == null) {
            results.put(key, new AggregationResult(event.getGameName(), event.getEventName(), eventTime));
            uniguqeTimeStamps.put(key, new HashSet<Long>(Arrays.asList(eventTime)));
            return;
        }

        //increment the total numbers
        record.incTotalEvents();

        // comparing the earliest timestamp
        record.updateTimestamps(eventTime);

        // update the number of unique timestamps
        uniguqeTimeStamps.get(key).add(eventTime);
        record.setUniqueTimestamps(uniguqeTimeStamps.get(key).size()); 
    }

    /**
     * Return the aggregated result for the provided eventName
     */
    public AggregationResult getAggregationResult(String gameName, String eventName) {
        System.out.println("Looking up aggregation for " + eventName);
        var key =  generateKey(gameName, eventName);
        return results.get(key);
    }
}
