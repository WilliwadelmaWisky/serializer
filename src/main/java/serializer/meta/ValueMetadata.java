package serializer.meta;

/**
 *
 */
public class ValueMetadata implements Metadata {

    private final String _name;
    private final String _value;


    /**
     * @param name
     * @param value
     */
    public ValueMetadata(String name, String value) {
        _name = name;
        _value = value;
    }


    @Override
    public String getName() {
        return _name;
    }

    public String getValue() {
        return _value;
    }
}
