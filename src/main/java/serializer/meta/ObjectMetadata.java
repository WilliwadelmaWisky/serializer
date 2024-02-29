package serializer.meta;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ObjectMetadata implements Metadata {

    private final String _name;
    private final Map<String, Metadata> _fieldMap;


    /**
     * @param name
     */
    public ObjectMetadata(String name) {
        _name = name;
        _fieldMap = new HashMap<>();
    }


    public ObjectMetadata(Object obj) {

    }


    @Override
    public String getName() { return _name; }


    public Metadata getField(String fieldName) {
        return _fieldMap.get(fieldName);
    }

    /**
     * @param metadata
     */
    public void append(Metadata metadata) {
        _fieldMap.put(metadata.getName(), metadata);
    }
}
