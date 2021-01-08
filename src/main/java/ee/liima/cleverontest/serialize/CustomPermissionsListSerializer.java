package ee.liima.cleverontest.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ee.liima.cleverontest.model.Permission;

import java.io.IOException;
import java.util.List;

public class CustomPermissionsListSerializer extends StdSerializer<List<Permission>> {

    public CustomPermissionsListSerializer() {
        this(null);
    }

    public CustomPermissionsListSerializer(Class<List<Permission>> t) {
        super(t);
    }

    @Override
    public void serialize(List<Permission> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        var permissionSerializer = new CustomPermissionSerializer();
        gen.writeStartArray();
        for (Permission permission : value) {
            permissionSerializer.serialize(permission, gen, provider);
        }
        gen.writeEndArray();

    }
}