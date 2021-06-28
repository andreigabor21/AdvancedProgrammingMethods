package Model.Statements;

import Model.ADTs.IDictionary;
import Model.ADTs.LockTable;
import Model.Exceptions.MyException;
import Model.ProgramState.ProgramState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;

import java.io.IOException;
import java.util.concurrent.locks.Lock;

public class LockStatement implements IStatement{

    private final String var;

    public LockStatement(String var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        var symbolTable = state.getSymbolTable();
        var lockTable = state.getLockTable();
        if(!symbolTable.isDefined(var))
            throw new MyException("Variable not declared (LockStatement)");
        if(!symbolTable.lookup(var).getType().equals(new IntType()))
            throw new MyException("Variable not defined as integer (LockStatement");
        int foundIndex = ((IntValue)symbolTable.lookup(var)).getValue();
        if(lockTable.lookup(foundIndex) == -1)
            lockTable.put(foundIndex, state.getId());
        else
            state.getExecutionStack().push(new LockStatement(var));
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
        return "lock(" + var + ")";
    }
}
