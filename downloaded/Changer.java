import java.util.ArrayList;

public class Changer {
    private ArrayList<Change> changer;

    public Changer() {
        this.changer = new ArrayList<Change>();
    }

    public void addChange(Change change) {
        this.changer.add(change);
    }

    public String change(String characterString) {
        for (Change change: this.changer) {
            characterString = change.change(characterString);
        }

        return characterString;
    }
}
