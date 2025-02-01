// This program was written by Tazrin Khalid - student # 251294394. This class implements a hash table data structure to store and retrieve game configurations (data objects). This uses a hash function to determine the position in the hashtable where each data object should be stored. 

import java.util.LinkedList;

public class HashDictionary implements DictionaryADT {
    
    private LinkedList<Data>[] hashTable; // Array of linked lists to store data
    
    private static final int x = 67; // Prime number for hashing
    private static final int prime = 9473; // Prime number for hashing

    // Constructor to initialize the hash table
    public HashDictionary(int size) {
        hashTable = new LinkedList[size]; // Initialize the array of linked lists
        for (int index = 0; index < size; index++) {
            hashTable[index] = new LinkedList<>(); // Initialize each linked list
        }
    }
    
    // Method to calculate hash value for a given configuration
    private int hash(String config) {
        int value = 0;
        for (int index = 0; index < config.length(); index++) {
            value = (value * x + config.charAt(index)) % prime; // Hash function
        }
        return value;
    }

    // Method to add a record to the dictionary
    @Override
    public int put(Data record) throws DictionaryException {
        String config = record.getConfiguration();
        int position = hash(config); // Calculate hash value
        LinkedList<Data> list = hashTable[position]; // Get the linked list at the hash position
        
        for (int loopIndex = 0; loopIndex < list.size(); loopIndex++) {
            Data data = list.get(loopIndex);
            if (data.getConfiguration().equals(config)) {
                throw new DictionaryException(); // Configuration already exists
            }
        }
        
        list.add(record); // Add the record to the linked list
        
        return list.size() > 1 ? 1 : 0; // Return 1 if collision, 0 otherwise
    }

    // Method to remove a record from the dictionary
    @Override
    public void remove(String config) throws DictionaryException {
        int index = hash(config); 
        LinkedList<Data> list = hashTable[index]; // Get the linked list at the hash position
        for (int loopIndex = 0; loopIndex < list.size(); loopIndex++) {
            Data data = list.get(loopIndex);
            if (data.getConfiguration().equals(config)) {
                list.remove(data); // Remove the data with the specified configuration
                return;
            }
        }
        throw new DictionaryException(); // Configuration not found
    }

    // Method to get the score for a given configuration
    @Override
    public int get(String config) {
        int position = hash(config);
        LinkedList<Data> list = hashTable[position]; // Get the linked list at the hash position
        if (list == null) {
            return -1; // No records at this position
        }
        for (int index = 0; index < list.size(); index++) {
            Data data = list.get(index);
            if (data.getConfiguration().equals(config)) {
                return data.getScore(); // Return the score for the specified configuration
            }
        }
        return -1; // Configuration not found
    }

    // Method to get the total number of records in the dictionary
    @Override
    public int numRecords() {
        int count = 0;
        for (int index = 0; index < hashTable.length; index++) {
            LinkedList<Data> list = hashTable[index]; // Get the linked list at the index
            count += list.size(); // Add the size of the linked list to the count
        }
        return count; // Return the total number of records
    }
}
