package Model.ADTs;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Barrier<T> implements IBarrier<T>{

    private Map<Integer, T> barrierTable;
    private Integer freeAddress;

    public Barrier() {
        barrierTable = new ConcurrentHashMap<>();
        freeAddress = 1;
    }

    @Override
    public Map getBarrierTable() {
        return barrierTable;
    }

    @Override
    public void put(Integer address, T value) {
        barrierTable.put(address, value);
    }

    @Override
    public T lookup(Integer address) {
        return barrierTable.get(address);
    }

    @Override
    public synchronized int getFreeAddress() {
        return freeAddress++;
    }

    @Override
    public String toString() {
        return "Barrier{" +
                "barrierTable=" + barrierTable +
                ", freeAddress=" + freeAddress +
                '}';
    }
}
