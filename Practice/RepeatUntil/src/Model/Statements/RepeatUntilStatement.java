package Model.Statements;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.Expressions.NotExpression;
import Model.Expressions.RelationalExpression;
import Model.Expressions.ValueExpression;
import Model.ProgramState.ProgramState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import View.Statements;

import java.io.IOException;

public class RepeatUntilStatement implements IStatement{

    private final IStatement stmt1;
    private final Expression exp2;

    public RepeatUntilStatement(IStatement stmt1, Expression exp2) {
        this.stmt1 = stmt1;
        this.exp2 = exp2;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        IStatement repStmt = Statements.buildCompound(
                stmt1,
                new WhileStatement(
                        new NotExpression(exp2),
                        stmt1)
        );
        state.getExecutionStack().push(repStmt);
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        var typeExp2 = exp2.typecheck(typeEnv);
        if(!typeExp2.equals(new BoolType())) {
            throw new MyException("The expression from RepeatUntil is not boolean");
        }
        stmt1.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "repeat " + stmt1 + " until " + exp2;
    }
}
