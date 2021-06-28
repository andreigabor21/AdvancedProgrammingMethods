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

public class UnlockStatement implements IStatement{

    private final String var;

    public UnlockStatement(String var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        Lock lock = new ReentrantLock();
        lock.lock();

        var symbolTable = state.getSymbolTable();
        var lockTable = state.getLockTable();
        if(!symbolTable.isDefined(var))
            throw new MyException("Variable not declared (UnlockStatement)");
        if(!symbolTable.lookup(var).getType().equals(new IntType()))
            throw new MyException("Variable not defined as integer (UnlockStatement");
        int foundIndex = ((IntValue)symbolTable.lookup(var)).getValue();
        if(lockTable.lookup(foundIndex).equals(state.getId()))
            lockTable.put(foundIndex, -1);
        lock.unlock();
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        if(typeEnv.isDefined(var)) {
            var varType = typeEnv.lookup(var);
            if(varType.equals(new IntType()))
                return typeEnv;
        }
        throw new MyException("Variable not properly instantiated (NewLockStatement");
    }

    @Override
    public String toString() {
        return "unlock(" + var + ")";
    }
}
