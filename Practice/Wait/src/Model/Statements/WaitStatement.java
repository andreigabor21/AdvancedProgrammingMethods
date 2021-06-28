package Model.Statements;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.ValueExpression;
import Model.ProgramState.ProgramState;
import Model.Types.Type;
import Model.Values.IntValue;
import View.Statements;

import java.io.IOException;

public class WaitStatement implements IStatement{

    private final Integer number;

    public WaitStatement(Integer number) {
        this.number = number;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        if(number == 0)
            return null;
        else {
            IStatement waitStmt = Statements.buildCompound(
                    new PrintStatement(new ValueExpression(new IntValue(number))),
                    new WaitStatement(number - 1)
            );
            state.getExecutionStack().push(waitStmt);
        }
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "wait(" + number.toString() + ")";
    }
}
