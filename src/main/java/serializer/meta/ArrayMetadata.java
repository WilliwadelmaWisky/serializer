package serializer.meta;

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
    public ArrayMetadata(String name) {
        _name = name;
        _valueList = new LinkedList<>();
    }


    @Override
    public String getName() {
        return _name;
    }


    public int getLength() {
        return _valueList.size();
    }

    public Iterable<Metadata> getValues() {
        return _valueList;
    }
}
