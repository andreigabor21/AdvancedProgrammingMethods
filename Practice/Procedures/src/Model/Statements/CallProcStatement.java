package Model.Statements;

import Model.ADTs.IDictionary;
import Model.ADTs.MyDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState.ProgramState;
import Model.Types.Type;
import Model.Values.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CallProcStatement implements IStatement{

    private final String fname;
    private final List<Expression> expressionList;

    public CallProcStatement(String fname, List<Expression> expressionList) {
        this.fname = fname;
        this.expressionList = expressionList;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        var procTable = state.getProcTable();
        var pair = procTable.lookup(fname); //error if not exists
        ArrayList<String> variables = (ArrayList<String>)pair.getKey();
        IStatement body = pair.getValue();
        var symbolTable = state.getSymbolTable();
        IDictionary<String, Value> newSymbolTable = new MyDictionary<>();
        for(int i = 0; i < variables.size(); ++i) {
            newSymbolTable.update(variables.get(i), expressionList.get(i).evaluate(symbolTable, state.getHeap()));
        }
        state.getSymbolTableStack().push(newSymbolTable);
        state.getExecutionStack().push(new ReturnStatement());
        state.getExecutionStack().push(body);
        return null;
    }

    @Override
    public IDictionary<String, Type> typecheck(IDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "call " + fname + expressionList.toString();
    }
}
