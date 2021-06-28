package Model.Statements;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.ProgramState.ProgramState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownStatement implements IStatement{

    private final String var;

    public CountDownStatement(String var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        Lock lock = new ReentrantLock();
        lock.lock();
        var symbolTable = state.getSymbolTable();
        if(!symbolTable.isDefined(var) || !symbolTable.lookup(var).getType().equals(new IntType()))
            throw new MyException("CountDown Error");
        var foundIndex = ((IntValue)symbolTable.lookup(var)).getValue();
        var latchTable = state.getLatchTable();
        var latchValue = latchTable.lookup(foundIndex);
        if(latchValue > 0) {
            latchTable.put(foundIndex, latchValue - 1);
        }
        state.getOutput().add(new IntValue(state.getId()));
        lock.unlock();
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        var typeVar = typeEnv.lookup(var);
        if(typeVar.equals(new IntType()))
            return typeEnv;
        else
            throw new MyException("Not of int Type (Await Statement)");
    }

    @Override
    public String toString() {
        return "countDown(" + var + ")";
    }
}
