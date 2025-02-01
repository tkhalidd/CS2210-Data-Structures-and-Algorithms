// This class was written by Tazrin Khalid (251294394). This class represents a key object that consists of a label and a type that can be used in a tree.

public class Key {

    // Instance variables to store key information
    private String label;
    private int type;

    // Constructor to initialize the Key object with label and type
    public Key(String theLabel, int theType) {
        // Set the type and convert the label to lowercase for case-insensitive comparison
        this.type = theType;
        this.label = theLabel.toLowerCase();
    }

    // Getter method to retrieve the label of the key
    public String getLabel() {
        return label;
    }

    // Getter method to retrieve the type of the key
    public int getType() {
        return type;
    }

    // Compare method to determine the order of two keys
    public int compareTo(Key k) {
        // Compare labels first, then types if labels are equal
        if (this.label.equals(k.label)) {
            return Integer.compare(this.type, k.type);
        } else {
            return this.label.compareTo(k.label);
        }
    }
}
