# kafka-json
JSON (de)serialization for kafka-client

Generic implementation to support POJO messages.

Using this project the exact Serializer/Deserializer is implemented much simplier, e.g.:

    public class DummyEntitySerializer extends JsonSerializer<DummyEntity> {
    }

    public class DummyEntityDeserializer extends JsonDeserializer<DummyEntity> {
    }

This is achieved with Jackson JSON project:
http://wiki.fasterxml.com/JacksonHome

No specific configuration is done for Jackson's ObjectMapper as of now, but it's predicted in the design.
