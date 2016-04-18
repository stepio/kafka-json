package org.stepio.kafka.common.serialization;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Before;
import org.junit.Test;
import org.stepio.kafka.common.serialization.test.DummyEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author stepio
 */
public class SimpleTest {

    private StringSerializer stringWriter;
    private StringDeserializer stringReader;
    private DummyEntitySerializer jsonWriter;
    private DummyEntityDeserializer jsonReader;
    private DummyEntity entity;
    private String topic;

    private class DummyEntitySerializer extends JsonSerializer<DummyEntity> {
    }

    private class DummyEntityDeserializer extends JsonDeserializer<DummyEntity> {
    }

    @Before
    public void init() {
        entity = new DummyEntity();
        entity.intValue = 19;
        entity.longValue = 7l;
        entity.stringValue = "dummy";
        List<String> list = Arrays.asList(new String[]{"dummy1", "dummy2"});
        entity.complexStruct = new HashMap<Short, List<String>>();
        entity.complexStruct.put((short) 4, list);

        topic = "topic-name";

        jsonReader = new DummyEntityDeserializer();
        jsonReader.configure(new HashMap<String, Object>(), false);
        jsonWriter = new DummyEntitySerializer();
        jsonWriter.configure(new HashMap<String, Object>(), false);
        stringReader = new StringDeserializer();
        stringReader.configure(new HashMap<String, Object>(), false);
        stringWriter = new StringSerializer();
        stringWriter.configure(new HashMap<String, Object>(), false);
    }

    /*
     * 1. Serialize test entity to byte array.
     * 2. Deserialize it back from the created byte array.
     * 3. Check the result with the source entity.
     */
    @Test
    public void deserialize_serialized_entity_equals() {
        assertEquals(entity, jsonReader.deserialize(topic, jsonWriter.serialize(topic, entity)));
    }

    /*
     * 1. Serialize "dummy" String to byte array.
     * 2. Deserialize it back from the created byte array.
     *    - this operation should fail as the source is not json
     *    - but the exception is logged, not rethrown - this unblocks the consuming of another messages
     * 3. In case of exception result is "null".
     */
    @Test
    public void deserialize_serialized_dummy_equals_null() {
        assertEquals(null, jsonReader.deserialize(topic, stringWriter.serialize(topic, "dummy")));
    }

    @Test
    public void serialized_string_null_equals_null() {
        assertEquals(null, stringWriter.serialize(topic, null));
    }

    @Test
    public void serialized_json_null_equals_null() {
        assertEquals(null, jsonWriter.serialize(topic, null));
    }



    @Test
    public void deserialized_string_null_equals_null() {
        assertEquals(null, stringReader.deserialize(topic, null));
    }

    @Test
    public void deserialized_json_null_equals_null() {
        assertEquals(null, jsonReader.deserialize(topic, null));
    }
}
