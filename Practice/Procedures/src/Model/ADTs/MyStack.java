package Model.ADTs;

import Model.Exceptions.MyException;
import Model.Values.Value;

import java.util.*;

public class MyStack<T> implements IStack<T> {

    private final Deque<T> stack;

    public MyStack() {
        this.stack = new LinkedList<>();
    }

    @Override
    public T pop() throws MyException {
        if(stack.size() == 0)
            throw new MyException("Stack is empty");
        return stack.pop();
    }

    @Override
    public T top() {
        return stack.peek();
    }

    @Override
    public void push(T value) {
        stack.push(value);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public List<T> asList() {
        List<T> list = new ArrayList<>(stack);
        return list;
    }

    @Override
    public IStack<T> clone() {
        IStack<T> copy = new MyStack<>();
        for(T elem : stack) {
            copy.push(elem);
        }
        return copy;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (T el : stack) {
            result.append(el)
                    .append("|");
        }
        result.append("}");
        return result.toString();
    }
}
