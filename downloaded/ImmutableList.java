package pr07_ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class ImmutableList {

    private List<Integer> numbers;

    public ImmutableList(List<Integer> numbers) {
        this.numbers = new ArrayList<>();
        this.numbers = numbers;
    }

    public ImmutableList getNumbers() {
        return new ImmutableList(this.numbers);
    }
}
