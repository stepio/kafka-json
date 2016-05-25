# kafka-json

[![Build Status](https://travis-ci.org/stepio/kafka-json.svg?branch=master)](https://travis-ci.org/stepio/kafka-json)

Generic implementation to support POJO messages in Kafka. Object is serialized to json and deserialized back with minimum extra code.

Using this project the exact Serializer/Deserializer implemented easily:

    public class DummyEntitySerializer extends JsonSerializer<DummyEntity> {
    }

    public class DummyEntityDeserializer extends JsonDeserializer<DummyEntity> {
    }

This is achieved with Jackson JSON project:
http://wiki.fasterxml.com/JacksonHome

No specific configuration is done for Jackson's ObjectMapper as of now, but it's predicted in the design.

The project is not supported anymore, as it's merged into [spring-kafka](https://github.com/spring-projects/spring-kafka).
The appropriate pull request is here:
https://github.com/spring-projects/spring-kafka/pull/81

Implementation is significantly improved by @artembilan, although it's not backward-compatible in some cases.
