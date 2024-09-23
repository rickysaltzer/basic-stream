package com.fortisgames.events.aggregation;

import com.fortisgames.events.Event;
import com.fortisgames.events.aggregation.AggregationResult;

public class InMemoryEventAggregator implements EventAggregator{
    /**
     * Collect an incoming event
     */
    public void collectEvent(Event event) {
        // TODO: Implement
    }

    /**
     * Return the aggregated result for the provided eventName
     */
    public AggregationResult getAggregationResult(String gameName, String eventName) {
        // TODO Implement
        System.out.println("Looking up aggregation for " + eventName);
        return null;
    }
}
