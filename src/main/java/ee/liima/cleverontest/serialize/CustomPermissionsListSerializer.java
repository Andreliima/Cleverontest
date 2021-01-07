package ee.liima.cleverontest.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ee.liima.cleverontest.model.Permission;

import java.io.IOException;
import java.util.List;

public class SubPermissionsListSerializer extends StdSerializer<List<Permission>> {

    public SubPermissionsListSerializer() {
        this(null);
    }

    public SubPermissionsListSerializer(Class<List<Permission>> t) {
        super(t);
    }

    @Override
    public void serialize(List<Permission> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeStringField("name", value.getName());
        gen.writeEndObject();

    }
}