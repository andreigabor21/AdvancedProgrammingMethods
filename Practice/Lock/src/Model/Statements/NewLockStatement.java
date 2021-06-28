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

public class NewLockStatement implements IStatement{

    private final String var;

    public NewLockStatement(String var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        Lock lock = new ReentrantLock();
        lock.lock();

        var symbolTable = state.getSymbolTable();
        var lockTable = state.getLockTable();
        int newFreeLocation = lockTable.getFreeLocation();
        lockTable.put(newFreeLocation, -1);
        if(symbolTable.isDefined(var)) {
            var varType = symbolTable.lookup(var).getType();
            if(varType.equals(new IntType())) {
                symbolTable.update(var, new IntValue(newFreeLocation));
            }
            else
                throw new MyException("Variable is not of type int (NewLockStatement)");
        }
        else
            throw new MyException("Variable is not defined in the Symbol Table (NewLockStatement");
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
        return "newLock(" + var + ")";
    }
}
