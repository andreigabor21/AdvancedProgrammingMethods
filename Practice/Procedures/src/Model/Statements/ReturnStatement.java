package Model.Statements;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.ProgramState.ProgramState;
import Model.Types.Type;

import java.io.IOException;

public class ReturnStatement implements IStatement{

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        var symbolTableStack = state.getSymbolTableStack();
        var top = symbolTableStack.pop();
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
