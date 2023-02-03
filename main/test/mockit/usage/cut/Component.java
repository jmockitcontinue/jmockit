package mockit.usage.cut;

public class Component {
    protected Component() {

    }

    public int getBaseline(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException(
                    "Width and height must be >= 0");
        }
        return -1;
    }

    private boolean checkCoalescing() {
        return false;
    }
}
