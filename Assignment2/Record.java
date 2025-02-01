// This class was written by Tazrin Khalid (251294394). It represents an object that combines key and data information. 

public class Record {

    // Instance variables to store key and data information
    private Key theKey;
    private String data;

    // Constructor to initialize the Record object with a key and data
    public Record(Key k, String theData) {
        // Set the key and data for the record
        this.data = theData;
        this.theKey = k;
    }

    // Getter method to retrieve the key of the record
    public Key getKey() {
        return theKey;
    }

    // Getter method to retrieve the data of the record
    public String getDataItem() {
        return data;
    }

}
