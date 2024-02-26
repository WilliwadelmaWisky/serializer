package serializer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Metadata {

    private final String _name;
    private final Map<String, Metadata> _fieldMap;


    /**
     * @param name
     */
    public Metadata(String name) {
        _name = name;
        _fieldMap = new HashMap<>();
    }


    public Metadata getField(String fieldName) {
        return _fieldMap.get(fieldName);
    }

    /**
     * @param metadata
     */
    public void append(Metadata metadata) {
        _fieldMap.put(metadata._name, metadata);
    }
}
