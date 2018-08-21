import java.util.ArrayList;
import java.util.List;

public class Element {
    public int value;
    public List<Integer> smaller;

    public Element(int value) {
        this.value = value;
        smaller = new ArrayList<Integer>();
    }

    public Element(int value, List<Integer> smaller) {
        this.value = value;
        this.smaller = smaller;
    }
}