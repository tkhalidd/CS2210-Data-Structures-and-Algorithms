// This program was written by Tazrin Khalid - student # 251294394. This class represents the object used in a game configuration and its associated score.

public class Data {
    // Variables to store configuration and score
    private String config;
    private int score;
    
    // Constructor to initialize configuration and score
    public Data(String config, int score) {
        this.score = score;
        this.config = config;
    }
    
    // Method to retrieve the configuration
    public String getConfiguration() {
        return config;
    }
    
    // Method to retrieve the score
    public int getScore() {
        return score;
    }
}
