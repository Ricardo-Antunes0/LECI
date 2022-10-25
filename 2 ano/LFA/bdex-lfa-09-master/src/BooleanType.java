public class BooleanType extends Type {
    public BooleanType() {
        super("Boolean");
    }

    @Override
    public boolean isBoolean() {
        return true;
    }
}
