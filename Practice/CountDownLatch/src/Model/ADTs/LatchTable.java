package Model.ADTs;

import Model.Exceptions.MyException;

import java.util.HashMap;
import java.util.Map;

public class LatchTable implements ILatchTable{

    private Map<Integer, Integer> map;
    private int address;

    public LatchTable() {
        map = new HashMap<>();
        address = 1;
    }

    @Override
    public synchronized void put(int k, int v) {
        map.put(k, v);
    }

    @Override
    public synchronized int lookup(int k) throws MyException {
        if(map.containsKey(k))
            return map.get(k);
        else
            throw new MyException("No suck key in Latch Table");
    }

    @Override
    public synchronized int getFreeAddress() {
        return address++;
    }

    @Override
    public Map<Integer, Integer> getContent() {
        return map;
    }
}
