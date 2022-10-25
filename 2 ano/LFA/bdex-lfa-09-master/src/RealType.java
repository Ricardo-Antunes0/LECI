public class RealType extends Type {
    public RealType() {
        super("Real");
    }

    @Override
    public boolean isNumeric() {
        return true;
    }

}
