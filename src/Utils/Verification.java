package Utils;

public class Verification {
    public void isNegative() {

    }

    public void isNull(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
    }
}
