package Model.ADTs;

import java.util.Map;

public interface ISemaphore<T> {
    Map<Integer, T> getSemaphoreTable();
    void put(Integer address, T value);
    T lookup(Integer addres);
    int getFreeAddress();
}
