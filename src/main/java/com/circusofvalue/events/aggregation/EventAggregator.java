package com.circusofvalue.events.aggregation;

import com.circusofvalue.events.Event;

public interface EventAggregator {

    public void collectEvent(Event event);

    public AggregationResult getAggregationResult(String gameName, String eventName);
}
