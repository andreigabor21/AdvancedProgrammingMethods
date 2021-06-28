package Model.Expressions;

import Model.ADTs.IDictionary;
import Model.ADTs.IHeap;
import Model.Exceptions.MyException;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class MULExpression implements Expression{

    private final Expression exp1;
    private final Expression exp2;

    public MULExpression(Expression exp1, Expression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> table, IHeap<Value> heap) throws MyException {
        Value value1,value2;
        value1 = exp1.evaluate(table,heap);
        if(value1.getType().equals(new IntType())){
            value2 = exp2.evaluate(table,heap);
            if(value2.getType().equals(new IntType())){
                IntValue toInteger1 = (IntValue)value1;
                IntValue toInteger2 = (IntValue)value2;
                int number1,number2;
                number1 = toInteger1.getValue();
                number2 = toInteger2.getValue();

                return new IntValue((number1 * number2) - (number1 + number2));
                }
            else
                throw new MyException("Second operand is not an int.");
        }
        else throw new MyException("First operand is not an int.");
    }

    @Override
    public Type typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1 = exp1.typecheck(typeEnv);
        type2 = exp2.typecheck(typeEnv);

        if(type1.equals(new IntType())) {
            if(type2.equals(new IntType())) {
                return new IntType();
            }
            else
                throw new MyException("Second operand is not an integer");
        }
        else
            throw new MyException("First operand is not an integer");
    }

    @Override
    public String toString() {
        return "(" + exp1.toString() + "*" + exp2.toString() + ")-(" + exp1.toString() + "+" + exp2.toString() + ")";
    }
}
