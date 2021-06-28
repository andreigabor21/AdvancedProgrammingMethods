package Model.Expressions;

import Model.ADTs.IDictionary;
import Model.ADTs.IHeap;
import Model.Exceptions.MyException;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

public class NotExpression implements Expression {

    private final Expression expression;

    public NotExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> table, IHeap<Value> heap) throws MyException {
        var eval = (BoolValue) expression.evaluate(table, heap);
        if(!eval.getValue())
            return new BoolValue(true);
        else
            return new BoolValue(false);
    }

    @Override
    public Type typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        var type = expression.typecheck(typeEnv);
        if(type.equals(new BoolType())) {
                return new BoolType();
        }
        else
            throw new MyException("Not a Boolean Type in Not Expression");
    }

    @Override
    public String toString() {
        return "!(" + expression + ")";
    }
}
