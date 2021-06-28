package Model.Statements;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState.ProgramState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewBarrierStatement implements IStatement{

    private final String var;
    private final Expression exp;
    private static final Lock lock = new ReentrantLock();

    public NewBarrierStatement(String var, Expression exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        lock.lock();
        var evaluatedExp = exp.evaluate(state.getSymbolTable(), state.getHeap());
        lock.unlock();
        if(!evaluatedExp.getType().equals(new IntType()))
            throw new MyException("Expression does not evaluate to integer type");
        Integer number = ((IntValue)evaluatedExp).getValue();
        var barrierTable = state.getBarrierTable();
        int newFreeLocation = barrierTable.getFreeAddress();
        Pair<Integer, List<Integer>> pair = new Pair<>(number, new ArrayList<>());
        barrierTable.put(newFreeLocation, pair);

        if(state.getSymbolTable().isDefined(var) && state.getSymbolTable().lookup(var).getType().equals(new IntType())) {
            lock.lock();
            state.getSymbolTable().update(var, new IntValue(newFreeLocation));
            lock.unlock();
        }
        else
            throw new MyException("Variable not defined in Symbol Table or not an integer (NewBarrierStatement)");

        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = exp.typecheck(typeEnv);
        Type typeVar = typeEnv.lookup(var);
        if(!typeExp.equals(new IntType()))
            throw new MyException("Expresion not of type Integer (NewBarrierStatement");
        if(!typeVar.equals(new IntType()))
            throw new MyException("Variable not of type Integer (NewBarrierStatement");
        return typeEnv;
    }

    @Override
    public String toString() {
        return "newBarrier(" + var + "," + exp + ")";
    }
}
