package Model.ADTs;

import Model.Exceptions.MyException;

import java.util.Map;

public interface ILatchTable {
    void put(int k, int v);
    int lookup(int k) throws MyException;
    int getFreeAddress();
    Map<Integer,Integer> getContent();
}
