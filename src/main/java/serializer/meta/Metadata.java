package serializer.meta;

/**
 *
 */
public interface Metadata {
    String getName();
    <T> T construct(Class<T> objType);
}
