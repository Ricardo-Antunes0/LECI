import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tupple {
    private final ArrayList<Object> values;
    private final ArrayList<Type> types;
    
    public Tupple( ArrayList<Object> values, ArrayList<Type> types) {
        this.values=values;
        this.types=types;
    }
    
    public Object getValue(int index){
        assert(index >= 0 && index < values.size()):"Expected values between 0 and "+ (values.size()-1);
        return values.get(index);
    }

    public Type getType(int index){
        assert(index >= 0 && index < values.size()):"Expected values between 0 and "+ (values.size()-1);
        return types.get(index);
    }

    public String toString() {
        String res = "( ";
        for(int i = 0; i < values.size(); i++) {
            if(i == values.size()-1)
                res += values.get(i);
            else
                res += values.get(i) + ", ";
        }
        return res + " )";
    }

}

