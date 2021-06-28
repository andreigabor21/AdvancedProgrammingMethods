package Model.Statements;

import Model.ADTs.IDictionary;
import Model.Exceptions.MyException;
import Model.ProgramState.ProgramState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import javafx.util.Pair;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AcquireStatement implements IStatement{

    private final String var;

    public AcquireStatement(String var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        Lock lock = new ReentrantLock();
        lock.lock();
        var symbolTable = state.getSymbolTable();
        var semaphoreTable = state.getSemaphoreTable();
        if(!symbolTable.isDefined(var) || !symbolTable.lookup(var).getType().equals(new IntType()))
            throw new MyException("Bad variable (AcquireStatement)");
        var foundIndex = ((IntValue)symbolTable.lookup(var)).getValue();
        if(semaphoreTable.lookup(foundIndex) == null)
            throw new MyException("Index not in Semahore Table (AcquireStatement)");
        var pair = semaphoreTable.lookup(foundIndex);

        int N1 = pair.getKey();
        int NL = pair.getValue().size();
        var lst = pair.getValue();
        if(N1 > NL) {
            if(!lst.contains(state.getId())) {
                lst.add(state.getId());
                //state.getSemaphoreTable().getBarrierTable().put(foundIndex, new Pair<>(N1, lst));
            }
        }
        else
            state.getExecutionStack().push(new AcquireStatement(var));

        lock.unlock();
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        var typeVar = typeEnv.lookup(var);
        if(typeVar.equals(new IntType()))
            return typeEnv;
        throw new MyException("Bad type (AcquireStatement)");
    }

    @Override
    public String toString() {
        return "acquire(" + var + ")";
    }
}
