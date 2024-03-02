package serializer.meta;

import serializer.PrimitiveUtil;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class ArrayMetadata implements Metadata {

    private final String _name;
    private final List<Metadata> _valueList;


    /**
     * @param name
     */
    public ArrayMetadata(String name, Object array) {
        _name = name;
        _valueList = new LinkedList<>();

        for (int i = 0; i < Array.getLength(array); i++) {
            Object element = Array.get(array, i);
            _valueList.add(createValueMetadata(name, element));
        }
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
        Class<?> componentType = objType.getComponentType();
        Object array = Array.newInstance(componentType, _valueList.size());
        int index = 0;

        for (Metadata metadata : _valueList) {
            Object value = metadata.construct(componentType);
            Array.set(array, index, value);
            index++;
        }

        return objType.cast(array);
    }


    private Metadata createValueMetadata(String name, Object obj) {
        if (obj.getClass() == Integer.class) {
            return new IntMetadata(name, (Integer)obj);
        }

        return null;
    }
}
