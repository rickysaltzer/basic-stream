package com.fortisgames.events.aggregation;

import com.fortisgames.events.Event;

public interface EventAggregator {

    public void collectEvent(Event event);

    public AggregationResult getAggregationResult(String gameName, String eventName);
}
