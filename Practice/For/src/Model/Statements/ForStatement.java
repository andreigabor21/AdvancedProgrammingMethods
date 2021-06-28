package Model.Statements;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.Expressions.RelationalExpression;
import Model.Expressions.VariableExpression;
import Model.ProgramState.ProgramState;
import Model.Types.IntType;
import Model.Types.Type;
import View.Statements;

import java.io.IOException;

public class ForStatement implements IStatement{

    private final String v;
    private final Expression exp1;
    private final Expression exp2;
    private final Expression exp3;
    private final IStatement stmt;

    public ForStatement(String v, Expression exp1, Expression exp2, Expression exp3, IStatement stmt) {
        this.v = v;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
        this.stmt = stmt;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
            IStatement forStmt = Statements.buildCompound(
                    new AssignmentStatement("v", exp1),
                    new WhileStatement(
                            new RelationalExpression(new VariableExpression("v"), exp2, "<"),
                    Statements.buildCompound(stmt, new AssignmentStatement("v", exp3))
                    )
            );
            state.getExecutionStack().push(forStmt);
            return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        var copyEnv = typeEnv.clone();
        Type typeExp1 = exp1.typecheck(copyEnv);
        Type typeExp2 = exp2.typecheck(copyEnv);
        Type typeExp3 = exp3.typecheck(copyEnv);
        var typeV = new VariableExpression("v").typecheck(copyEnv);
        if(typeExp1.equals(new IntType()) &&
                typeExp2.equals(new IntType()) &&
                typeExp3.equals(new IntType()) &&
                typeV.equals(new IntType())) {
            return typeEnv;
        }
        else
            throw new MyException("Not of type Int");
    }

    @Override
    public String toString() {
        return "for(" + v + "=" + exp1 + ";" + v + "<" + exp2 + ";" + v + "=" + exp3 + ")" + stmt;

    }
}
