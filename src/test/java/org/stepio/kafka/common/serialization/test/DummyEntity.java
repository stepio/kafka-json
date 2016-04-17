package org.stepio.kafka.common.serialization.test;

import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * @author stepio
 */
@EqualsAndHashCode
public class DummyEntity {

    public int intValue;
    public Long longValue;
    public String stringValue;
    public Map<Short, List<String>> complexStruct;
}
