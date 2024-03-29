package serializer.meta;

/**
 *
 */
public class BooleanMetadata implements Metadata {

    private final String _name;
    private final String _value;


    /**
     * @param name
     * @param value
     */
    public BooleanMetadata(String name, Boolean value) {
        _name = name;
        _value = value.toString();
    }


    /**
     * @return
     */
    @Override
    public String getName() {
        return _name;
    }


    /**
     * @param objType
     * @param <T>
     * @return
     */
    @Override
    public <T> T construct(Class<T> objType) {
        if (objType != Boolean.class)
            return null;

        return objType.cast(Boolean.valueOf(_value));
    }
}
