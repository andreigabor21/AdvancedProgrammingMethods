package Model.Statements;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.Expressions.RelationalExpression;
import Model.ProgramState.ProgramState;
import Model.Types.Type;

import java.io.IOException;

public class SwitchStatement implements IStatement{

    private final Expression exp;
    private final Expression exp1;
    private final Expression exp2;
    private final IStatement stmt1;
    private final IStatement stmt2;
    private final IStatement stmt3;

    public SwitchStatement(Expression exp, Expression exp1, Expression exp2, IStatement stmt1, IStatement stmt2, IStatement stmt3) {
        this.exp = exp;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
        this.stmt3 = stmt3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        IStatement switchS = new IfStatement(new RelationalExpression(exp, exp1, "=="),stmt1,
                                            new IfStatement(new RelationalExpression(exp, exp2, "=="),stmt2,stmt3));

        state.getExecutionStack().push(switchS);
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        if(! exp.typecheck(typeEnv).equals(exp1.typecheck(typeEnv)) && exp.typecheck(typeEnv).equals(exp2.typecheck(typeEnv)))
            throw new MyException("Types don't match");
        stmt1.typecheck(typeEnv);
        stmt2.typecheck(typeEnv);
        stmt3.typecheck(typeEnv);

        return typeEnv;
    }

    @Override
    public String toString() {
        return "switch(" + exp + ") (case " + exp1 + ": " + stmt1 + ")(case " + exp2 + ": " + stmt2
                + ")(" + "default: " + stmt3 + ")";
    }
}
