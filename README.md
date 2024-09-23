# Basic Streaming Test

## Instructions

### Step 1

Inspect [Event.java](src/main/java/com/circusofvalue/events/Event.java) to understand the type of event you will be receiving. 


### Step 2

Inspect [AggregationResult.java](src/main/java/com/circusofvalue/events/aggregation/AggregationResult.java) to understand
the aggregation you will be performing.

### Step 3

Inspect [EventAggregator.java](src/main/java/com/circusofvalue/events/aggregation/EventAggregator.java), which is the interface
you will be implementing to perform the aggregation.

### Step 4

**Implement** [InMemoryEventAggregator.java](src/main/java/com/circusofvalue/events/aggregation/InMemoryEventAggregator.java), a 
pre-stubbed class of the `EventAggregator` interface.

Reference [TestEventAggregator.java](src/test/java/com/circusofvalue/events/aggregation/TestEventAggregator.java) to understand
how the tests will be performed.

### Verifying Code

Run the tests using the provided Maven wrapper in the project
```shell
./mvnw test
```
