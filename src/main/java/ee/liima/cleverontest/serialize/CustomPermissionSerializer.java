package ee.liima.cleverontest.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ee.liima.cleverontest.model.Permission;

import java.io.IOException;

public class ParentPermissionSerializer extends StdSerializer<Permission> {

    public ParentPermissionSerializer() {
        this(null);
    }

    public ParentPermissionSerializer(Class<Permission> t) {
        super(t);
    }

    @Override
    public void serialize(Permission value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeStringField("name", value.getName());
        gen.writeFieldName("parent");
        gen.

    }
}
