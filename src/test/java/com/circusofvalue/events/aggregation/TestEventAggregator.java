package com.circusofvalue.events.aggregation;

import static org.junit.Assert.*;

import org.junit.Test;

import com.circusofvalue.events.Event;

public class TestEventAggregator {
    @Test
    public void consumeAndAggregateSingleEvent() {

        EventAggregator agg = new InMemoryEventAggregator();
        Event event = new Event("game1", "event1", 500L);
        agg.collectEvent(event);
        assertNull(agg.getAggregationResult("noGame", "shouldBeNull"));
        AggregationResult result = agg.getAggregationResult("game1", "event1");
        assertNotNull(result);
        assertEquals("event1", result.getEventName());
        assertEquals(500L, result.getEarliestTimestamp());
        assertEquals(500L, result.getLatestTimestamp());
        assertEquals(1, result.getTotalEvents());
    }

    @Test
    public void consumeAndAggregateMultipleEvents() {
        EventAggregator agg = new InMemoryEventAggregator();
        agg.collectEvent(new Event("game1", "event1", 500));
        agg.collectEvent(new Event("game1", "event1", 750));
        agg.collectEvent(new Event("game1", "event1", 250));
        agg.collectEvent(new Event("game1", "event1", 250));
        agg.collectEvent(new Event("game1", "event1", 1000));
        agg.collectEvent(new Event("game2", "event1", 1250));
        agg.collectEvent(new Event("game2", "event1", 750));
        agg.collectEvent(new Event("game1", "event2", 1250));
        agg.collectEvent(new Event("game1", "event2", 600));
        agg.collectEvent(new Event("game1", "event2", 600));
        agg.collectEvent(new Event("game1", "event2", 1200));
        agg.collectEvent(new Event("game2", "event2", 1200));

        assertNull(agg.getAggregationResult("noGame", "shouldBeNull"));

        AggregationResult result = agg.getAggregationResult("game1", "event1");
        assertNotNull(result);
        assertEquals("event1", result.getEventName());
        assertEquals("game1", result.getGameName());
        assertEquals(250L, result.getEarliestTimestamp());
        assertEquals(1000L, result.getLatestTimestamp());
        assertEquals(5, result.getTotalEvents());
        assertEquals(4, result.getUniqueTimestamps());


        result = agg.getAggregationResult("game2", "event1");
        assertNotNull(result);
        assertEquals("event1", result.getEventName());
        assertEquals("game2", result.getGameName());
        assertEquals(750L, result.getEarliestTimestamp());
        assertEquals(1250L, result.getLatestTimestamp());
        assertEquals(2, result.getTotalEvents());
        assertEquals(2, result.getUniqueTimestamps());

        result = agg.getAggregationResult("game1", "event2");
        assertNotNull(result);
        assertEquals("event2", result.getEventName());
        assertEquals("game1", result.getGameName());
        assertEquals(600L, result.getEarliestTimestamp());
        assertEquals(1250L, result.getLatestTimestamp());
        assertEquals(4, result.getTotalEvents());
        assertEquals(3, result.getUniqueTimestamps());


        result = agg.getAggregationResult("game2", "event2");
        assertNotNull(result);
        assertEquals("event2", result.getEventName());
        assertEquals("game2", result.getGameName());
        assertEquals(1200L, result.getEarliestTimestamp());
        assertEquals(1200L, result.getLatestTimestamp());
        assertEquals(1, result.getTotalEvents());
        assertEquals(1, result.getUniqueTimestamps());
    }

    @Test
    public void testWeakKeys() {
        EventAggregator agg = new WeakEventAggregator();
        agg.collectEvent(new Event("game1", "event1", 500));
        agg.collectEvent(new Event("game", "1event1", 750));

        AggregationResult result = agg.getAggregationResult("game1", "event1");
        assertEquals("game1", result.getGameName());
        assertEquals("event1", result.getEventName());
        assertEquals(1, result.getTotalEvents());

        result = agg.getAggregationResult("game", "1event1");
        assertEquals("game", result.getGameName());
        assertEquals("1event1", result.getEventName());
        assertEquals(1, result.getTotalEvents());
    }
}
