package serializer.format.json;

import serializer.format.Formatter;
import serializer.meta.Metadata;
import serializer.meta.ObjectMetadata;
import serializer.meta.ValueMetadata;

public class JsonFormatter implements Formatter {

    public JsonFormatter() {

    }

    @Override
    public String convert(Metadata metadata) {
        return null;
    }

    @Override
    public Metadata convert(String s) {
        // { "name": "string" }
        ObjectMetadata metadata = new ObjectMetadata("self");
        metadata.append(new ValueMetadata("name", "string"));
        return metadata;
    }
}
