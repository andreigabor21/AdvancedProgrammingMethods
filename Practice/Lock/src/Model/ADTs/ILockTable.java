package Model.ADTs;

import Model.Exceptions.MyException;

import java.util.Map;

public interface ILockTable {
    Integer lookup(int location) throws MyException;
    void put(int addr, int val);
    Map<Integer,Integer> getContent();
    Integer getFreeLocation();
}
