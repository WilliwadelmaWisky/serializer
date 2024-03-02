package serializer;

import serializer.format.Formatter;
import serializer.meta.ArrayMetadata;
import serializer.meta.Metadata;
import serializer.meta.ObjectMetadata;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 *
 */
public class Deserializer {

    /**
     * @param objClass
     * @param s
     * @param formatter
     * @param <T>
     * @return
     */
    public static <T> T deserialize(Class<T> objClass, String s, Formatter formatter) {
        Metadata metadata = formatter.convert(s);
        return createObject(objClass, (ObjectMetadata)metadata);
    }


    public static <T> T createObject(Class<T> objType, ObjectMetadata metadata) {
        try {
            Constructor<T> ctor = objType.getDeclaredConstructor();
            T obj = ctor.newInstance();

            for (Field field : objType.getDeclaredFields()) {
                if (Util.isFieldIgnored(field))
                    continue;

                Metadata fieldData = metadata.getField(field.getName());
                if (field.getType().isArray()) {
                    field.set(obj, createArray(field.getClass().getComponentType(), (ArrayMetadata)fieldData));
                    continue;
                }

                if (PrimitiveUtil.isPrimitiveType(field.getType())) {
                    field.set(obj, createValue(field.getType(), (ValueMetadata)fieldData));
                    continue;
                }

                field.set(obj, createObject(field.getType(), (ObjectMetadata)fieldData));
            }

            return obj;
        } catch (Exception ignored) { }

        return null;
    }

    public static Object createArray(Class<?> componentType, ArrayMetadata metadata) {
        Object array = Array.newInstance(componentType, metadata.getLength());
        int index = 0;

        for (Metadata data : metadata.getValues()) {

            Object value;
            if (PrimitiveUtil.isPrimitiveType(componentType)) {
                value = createValue(componentType, (ValueMetadata)data);
            } else {
                value = createObject(componentType, (ObjectMetadata)data);
            }

            Array.set(array, index, value);
            index++;
        }

        return array;
    }

    public static Object createValue(Class<?> valueType, ValueMetadata metadata) {
        if (valueType.equals(Integer.class)) {
            return Integer.parseInt(metadata.getValue());
        }

        if (valueType.equals(Long.class)) {
            return Long.parseLong(metadata.getValue());
        }

        return metadata.getValue();
    }
}
