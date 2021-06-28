package Model.Statements;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.ProgramState.ProgramState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;

import java.io.IOException;

public class AwaitStatement implements IStatement{

    private final String var;

    public AwaitStatement(String var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        var symbolTable = state.getSymbolTable();
        if(!symbolTable.isDefined(var) || !symbolTable.lookup(var).getType().equals(new IntType()))
            throw new MyException("AwaitStatement error");
        var foundIndex = ((IntValue)symbolTable.lookup(var)).getValue();
        var latchTable = state.getLatchTable();
        if(latchTable.lookup(foundIndex) == 0)
            return null;
        else
            state.getExecutionStack().push(new AwaitStatement(var));
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
        return "await(" + var + ")";
    }
}
