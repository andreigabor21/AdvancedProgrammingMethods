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

public class AwaitStatement implements IStatement{

    private final String var;
    private static final Lock lock = new ReentrantLock();

    public AwaitStatement(String var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        var symbolTable = state.getSymbolTable();
        var barrierTable = state.getBarrierTable();
        if(!symbolTable.isDefined(var) || !symbolTable.lookup(var).getType().equals(new IntType()))
            throw new MyException("Variable not in Symbol Table or not an integer (AwaitStatement)");
        var foundIndex = ((IntValue)symbolTable.lookup(var)).getValue();
        if(!barrierTable.getBarrierTable().containsKey(foundIndex))
            throw new MyException("Index not in Barrier Table (AwaitStatement)");
        var pair = barrierTable.lookup(foundIndex);

        int N1 = pair.getKey();
        int NL = pair.getValue().size();
        var lst = pair.getValue();
        if(N1 > NL) {
            if(lst.contains(state.getId())) {
                lock.lock();
                state.getExecutionStack().push(new AwaitStatement(var));
                lock.unlock();
            }
            else {
                lock.lock();
                lst.add(state.getId());
                state.getBarrierTable().getBarrierTable().put(foundIndex, new Pair<>(N1, lst));
                state.getExecutionStack().push(new AwaitStatement(var));
                lock.unlock();
            }
        }

        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        var typeVar = typeEnv.lookup(var);
        if(typeVar.equals(new IntType()))
            return typeEnv;
        throw new MyException("Variable not of Integer type (AwaitStatement)");
    }

    @Override
    public String toString() {
        return "await(" + var + ")";
    }
}
