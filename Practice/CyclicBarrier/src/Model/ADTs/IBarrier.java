package Model.ADTs;

import java.util.Map;

public interface IBarrier<T> {
    Map<Integer, T> getBarrierTable();
    void put(Integer address, T value);
    T lookup(Integer address);
    int getFreeAddress();
}
