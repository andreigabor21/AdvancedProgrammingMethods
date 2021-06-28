package Model.ADTs;

import java.util.Map;

public interface IProcTable<T> {
    void put(String name, T value);
    T lookup(String name);
    Map<String, T> getContent();
}
