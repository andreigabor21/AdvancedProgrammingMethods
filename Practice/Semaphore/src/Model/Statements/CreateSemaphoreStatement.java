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

public class CreateSemaphoreStatement implements IStatement{

    private final String var;
    private final Expression exp1;

    public CreateSemaphoreStatement(String var, Expression exp1) {
        this.var = var;
        this.exp1 = exp1;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        Lock lock = new ReentrantLock();
        lock.lock();
        var evaluatedExp = exp1.evaluate(state.getSymbolTable(), state.getHeap());
        if(!evaluatedExp.getType().equals(new IntType()))
            throw new MyException("Expression not of type Integer (CreateSemaphoreStatement)");
        Integer number1 = ((IntValue)evaluatedExp).getValue();
        var semaphoreTable = state.getSemaphoreTable();
        Integer newFreeLocation = semaphoreTable.getFreeAddress();
        Pair<Integer, List<Integer>> pair = new Pair<Integer, List<Integer>>(number1, new ArrayList<Integer>());
        semaphoreTable.put(newFreeLocation, pair);

        var symbolTable = state.getSymbolTable();
        if(symbolTable.isDefined(var) && symbolTable.lookup(var).getType().equals(new IntType()))
            state.getSymbolTable().update(var, new IntValue(newFreeLocation));
        else
            throw new MyException("Error in Symbol Table (CreateSemaphoreStatement)");

        lock.unlock();
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        var typeVar = typeEnv.lookup(var);
        var typeExp = exp1.typecheck(typeEnv);
        if(typeVar.equals(new IntType()) && typeExp.equals(new IntType()))
            return typeEnv;
        throw new MyException("Bad types (CreateSemaphoreStatement)");
    }

    @Override
    public String toString() {
        return "createSemaphore(" + var + "," + exp1.toString() + ")";
    }
}
