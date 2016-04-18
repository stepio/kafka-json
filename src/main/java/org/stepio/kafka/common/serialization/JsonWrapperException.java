package org.stepio.kafka.common.serialization;

import org.apache.kafka.common.KafkaException;

/**
 * Specific wrapper, extending {@link KafkaException}.
 *
 * @author stepio
 */
public class JsonWrapperException extends KafkaException {

    public JsonWrapperException(Throwable cause) {
        super(cause);
    }
}
