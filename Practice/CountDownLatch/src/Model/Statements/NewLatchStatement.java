package Model.Statements;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState.ProgramState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;

import java.io.IOException;

public class NewLatchStatement implements IStatement{

    private final String var;
    private final Expression exp;

    public NewLatchStatement(String var, Expression exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        var latchTable = state.getLatchTable();
        var numValue = exp.evaluate(state.getSymbolTable(), state.getHeap());
        if(!numValue.getType().equals(new IntType()))
            throw new MyException("Number for latch not of type int");
        int num1 = ((IntValue)numValue).getValue();
        int newFreeLocation = latchTable.getFreeAddress();
        latchTable.put(newFreeLocation, num1);
        if(state.getSymbolTable().isDefined(var) && state.getSymbolTable().lookup(var).getType().equals(new IntType()))
            state.getSymbolTable().update(var, new IntValue(newFreeLocation));
        else
            throw new MyException("Error in NewLatchStatement");
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        var typeVar = typeEnv.lookup(var);
        var typeExp = exp.typecheck(typeEnv);
        if(typeVar.equals(new IntType()) && typeExp.equals(new IntType()))
            return typeEnv;
        else
            throw new MyException("Not of int Type (NewLatch)");
    }

    @Override
    public String toString() {
        return "newLatch(" + var + "," + exp.toString() + ")";
    }
}
