package Model.ADTs;

import Model.Exceptions.MyException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LockTable implements ILockTable{

    private Map<Integer, Integer> map;
    private Integer freeLocation;

    public LockTable() {
        map = new ConcurrentHashMap<>();
        freeLocation = 1;
    }

    @Override
    public Integer lookup(int location) throws MyException {
        if(map.containsKey(location))
            return map.get(location);
        else
            throw new MyException("No such entry in the Lock Table");
    }

    @Override
    public void put(int addr, int val) {
        map.put(addr, val);
    }

    @Override
    public Map<Integer, Integer> getContent() {
        return map;
    }

    @Override
    public Integer getFreeLocation() {
        return freeLocation++;
    }
}
