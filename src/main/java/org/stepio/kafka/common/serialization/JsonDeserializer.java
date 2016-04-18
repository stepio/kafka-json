package org.stepio.kafka.common.serialization;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectReader;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
 * Generic {@link Deserializer} for sending Java objects to Kafka as JSON.
 *
 * @author stepio
 */
public class JsonDeserializer<T> implements Deserializer<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonDeserializer.class);

    private Class<T> type;
    private ObjectReader reader;

    public void configure(Map<String, ?> configs, boolean isKey) {
        LOGGER.debug("Start configuring");
        // Getting class object:
        // http://blog.xebia.com/acessing-generic-types-at-runtime-in-java/
        type = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        reader = JsonDatabindFactory.createDeserializer(type, configs, isKey);
        LOGGER.debug("Finish configuring");
    }

    public T deserialize(String topic, byte[] data) {
        try {
            LOGGER.debug("Start processing");
            T result = null;
            if (data != null) {
                result = reader.readValue(data);
            }
            LOGGER.debug("Finish processing");
            return result;
        } catch (JsonParseException ex) {
            LOGGER.error("Failed processing", ex);
            return null;
        } catch (IOException ex) {
            LOGGER.debug("Failed processing");
            throw new JsonWrapperException(ex);
        }
    }

    public void close() {
        LOGGER.debug("Nothing to close");
    }
}
