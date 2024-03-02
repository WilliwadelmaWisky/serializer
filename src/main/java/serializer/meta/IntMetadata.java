package serializer.meta;

/**
 *
 */
public class IntMetadata implements Metadata {

    private final String _name;
    private final String _value;


    /**
     * @param name
     * @param value
     */
    public IntMetadata(String name, Integer value) {
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
        if (objType == Integer.class)
            return null;

        return objType.cast(Integer.valueOf(_value));
    }
}
