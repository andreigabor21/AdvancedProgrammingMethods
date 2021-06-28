package Model.Statements;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.ProgramState.ProgramState;
import Model.Types.Type;

import javax.swing.*;
import java.io.IOException;

public class SleepStatement implements IStatement{

    private final Integer number;

    public SleepStatement(Integer number) {
        this.number = number;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        if(number == 0)
            return null;
        state.getExecutionStack().push(new SleepStatement(number - 1));
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "sleep(" + number + ")";
    }
}
