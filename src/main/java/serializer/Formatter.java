package serializer;

/**
 *
 */
public interface Formatter {

    /**
     * @param metadata
     * @return
     */
    String convert(Metadata metadata);

    /**
     * @param s
     * @return
     */
    Metadata convert(String s);
}
