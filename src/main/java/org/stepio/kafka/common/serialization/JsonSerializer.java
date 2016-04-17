package org.stepio.kafka.common.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Generic {@code Serializer} for sending Java objects to Kafka as JSON.
 *
 * @author stepio
 */
public class JsonSerializer<T> implements Serializer<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonSerializer.class);

    private ObjectWriter writer;

    public void configure(Map<String, ?> configs, boolean isKey) {
        LOGGER.debug("Start configuring");
        writer = JsonDatabindFactory.createSerializer(configs, isKey);
        LOGGER.debug("Finish configuring");
    }

    public byte[] serialize(String topic, T data) {
        try {
            LOGGER.debug("Start processing");
            byte [] result = null;
            if (data != null) {
                result = writer.writeValueAsBytes(data);
            }
            LOGGER.debug("Finish processing");
            return result;
        } catch (JsonProcessingException ex) {
            LOGGER.debug("Failed processing");
            throw new JsonWrapperException(ex);
        }
    }

    public void close() {
        LOGGER.debug("Nothing to close");
    }
}
