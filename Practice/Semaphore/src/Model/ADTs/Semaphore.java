package Model.ADTs;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Semaphore<T> implements ISemaphore<T>{

    private Map<Integer, T> map;
    private Integer freeLocation;

    public Semaphore() {
        map = new ConcurrentHashMap<>();
        freeLocation = 1;
    }

    @Override
    public Map getSemaphoreTable() {
        return map;
    }

    @Override
    public void put(Integer address, T value) {
        map.put(address, value);
    }

    @Override
    public T lookup(Integer addres) {
        return map.get(addres);
    }

    @Override
    public int getFreeAddress() {
        return freeLocation++;
    }
}
