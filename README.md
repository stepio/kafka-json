# kafka-json

[![Build Status](https://travis-ci.org/stepio/kafka-json.svg?branch=master)](https://travis-ci.org/stepio/kafka-json)

JSON (de)serialization for kafka-client

Generic implementation to support POJO messages in Kafka. Object is serialized to json and deserialized back with minimum extra code.

Using this project the exact Serializer/Deserializer implemented easily:

    public class DummyEntitySerializer extends JsonSerializer<DummyEntity> {
    }

    public class DummyEntityDeserializer extends JsonDeserializer<DummyEntity> {
    }

This is achieved with Jackson JSON project:
http://wiki.fasterxml.com/JacksonHome

No specific configuration is done for Jackson's ObjectMapper as of now, but it's predicted in the design.
