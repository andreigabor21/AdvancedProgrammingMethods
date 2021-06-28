package Model.ADTs;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProcTable<T> implements IProcTable<T>{

    Map<String, T> map;

    public ProcTable() {
        map = new ConcurrentHashMap<>();
    }

    @Override
    public void put(String name, T value) {
        map.put(name, value);
    }

    @Override
    public T lookup(String name) {
        return map.get(name);
    }

    @Override
    public Map<String, T> getContent() {
        return map;
    }
}
