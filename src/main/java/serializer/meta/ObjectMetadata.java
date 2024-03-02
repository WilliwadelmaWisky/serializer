package serializer.meta;

import serializer.Util;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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
     * @param obj
     */
    public ObjectMetadata(String name, Object obj) {
        _name = name;
        _fieldMap = new HashMap<>();
        createMetadata(obj);
    }


    /**
     * @return
     */
    @Override
    public String getName() { return _name; }

    /**
     * @param objType
     * @param <T>
     * @return
     */
    @Override
    public <T> T construct(Class<T> objType) {
        try {
            Constructor<T> ctor = objType.getDeclaredConstructor();
            T obj = ctor.newInstance();

            for (Field field : objType.getDeclaredFields()) {
                if (Util.isFieldIgnored(field))
                    continue;

                Metadata fieldData = _fieldMap.get(field.getName());
                Object fieldValue = fieldData.construct(field.getType());
                field.set(obj, fieldValue);
            }

            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void createMetadata(Object obj) {
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                if (Util.isFieldIgnored(field))
                    continue;

                field.setAccessible(true);
                Metadata metadata = createFieldMetadata(field.getName(), field.get(obj));
                _fieldMap.put(metadata.getName(), metadata);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Metadata createFieldMetadata(String name, Object obj) {
        if (obj.getClass().isArray()) {
            return new ArrayMetadata(name, obj);
        }

        return createValueMetadata(name, obj);
    }

    private Metadata createValueMetadata(String name, Object obj) {
        if (obj.getClass() == Integer.class) {
            return new IntMetadata(name, (Integer)obj);
        }

        return null;
    }
}
