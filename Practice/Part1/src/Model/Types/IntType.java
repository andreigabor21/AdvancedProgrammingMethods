package Model.Types;

import Model.Values.IntValue;

public class IntType implements Type {

    @Override
    public boolean equals(Object obj){
        return obj instanceof IntType;
    }

    @Override
    public String toString(){
       return "int ";
   }

   @Override
   public IntValue defaultValue(){return new IntValue(0);}
}


