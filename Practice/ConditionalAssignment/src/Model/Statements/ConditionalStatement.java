package Model.Statements;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.Expressions.VariableExpression;
import Model.ProgramState.ProgramState;
import Model.Types.BoolType;
import Model.Types.Type;

import java.io.IOException;

public class ConditionalStatement implements IStatement{

    private final String v;
    private final Expression exp1;
    private final Expression exp2;
    private final Expression exp3;

    public ConditionalStatement(String v, Expression exp1, Expression exp2, Expression exp3) {
        this.v = v;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        IStatement condStmt = new IfStatement(exp1,
                                                new AssignmentStatement(v, exp2),
                                                new AssignmentStatement(v, exp3));
        state.getExecutionStack().push(condStmt);
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp1 = exp1.typecheck(typeEnv);
        if(typeExp1.equals(new BoolType())) {
            Type typeExp2 = exp2.typecheck(typeEnv);
            Type typeExp3 = exp3.typecheck(typeEnv);
            var typev = new VariableExpression(v).typecheck(typeEnv);
            if(typeExp2.equals(typeExp3) && typev.equals(typeExp3))
                return typeEnv;
            else
                throw new MyException("Types do not match");


        }
        else
            throw new MyException("Not of boolean type!");
    }

    @Override
    public String toString() {
        return v + "=" + exp1 + "?" + exp2 + ":" + exp3;
    }
}
