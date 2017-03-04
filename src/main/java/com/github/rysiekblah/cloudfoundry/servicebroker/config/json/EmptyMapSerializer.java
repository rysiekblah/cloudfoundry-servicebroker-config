package com.github.rysiekblah.cloudfoundry.servicebroker.config.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Tomasz_Kozlowski on 3/4/2017.
 */
public class EmptyMapSerializer extends JsonSerializer<Map<?, ?>> {
    public EmptyMapSerializer() {
    }

    @Override
    public void serialize(Map<?, ?> value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeEndObject();
    }
}
