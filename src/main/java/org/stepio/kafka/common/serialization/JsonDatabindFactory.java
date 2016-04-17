package org.stepio.kafka.common.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Main class for jackson JSON configuration.
 *
 * @author stepio
 */
public class JsonDatabindFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonDatabindFactory.class);

    private static ObjectMapper MAPPER;
    private static ObjectWriter WRITER;

    static {
        // these classes are thread-safe, so using single instance per class is performance-efficient
        MAPPER = new ObjectMapper();
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        WRITER = MAPPER.writer();
    }

    /**
     * Creates {@class ObjectMapper} for the specified config, returns static instance as of now.
     */
    public static ObjectMapper createMapper(Map<String, ?> configs, boolean isKey) {
        LOGGER.debug("Start configuring mapper");
        // TODO: config-based customization can be implemented here
        ObjectMapper result = MAPPER;
        LOGGER.debug("Finish configuring mapper");
        return result;
    }

    /**
     * Creates {@class ObjectWriter} for the specified config, returns static instance as of now.
     */
    public static ObjectWriter createSerializer(Map<String, ?> configs, boolean isKey) {
        LOGGER.debug("Start configuring writer");
        // TODO: config-based customization can be implemented here
        ObjectWriter result = WRITER;
        LOGGER.debug("Finish configuring writer");
        return result;
    }

    /**
     * Creates {@class ObjectReader} for the specified class & config, ignores config as of now.
     */
    public static ObjectReader createDeserializer(Class type, Map<String, ?> configs, boolean isKey) {
        LOGGER.debug("Start configuring reader");
        ObjectReader result = createMapper(configs, isKey).readerFor(type);
        LOGGER.debug("Finish configuring reader");
        return result;
    }
}
