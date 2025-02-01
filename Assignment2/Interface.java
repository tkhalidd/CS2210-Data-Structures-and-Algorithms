// This class was written by Tazrin Khalid (251294394). This class populates a dictionary and tree from a file and processes user commands.

import java.io.*;

public class Interface {

    public static void main(String[] args) throws Exception {
        BSTDictionary bstDict = new BSTDictionary();
        BinarySearchTree bst = new BinarySearchTree();

        // if there is no input
        if (args.length < 1) {
            System.out.println("No file is being read in.");
        } else {
            String inputFile = args[0];
            int type = 0;
            String data = null;
            boolean gotType = false;

            try {
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                String line1, line2;
                line1 = reader.readLine();

                while ((line2 = reader.readLine()) != null) {
                    char ch = line2.charAt(0);

                    if (ch == '-' || ch == '+' || ch == '*' || ch == '/') {
                        String dataName = line2.substring(1).trim();

                        if (ch == '-') {
                            type = 3;
                        } else if (ch == '+') {
                            type = 4;
                        } else if (ch == '*') {
                            type = 5;
                        } else if (ch == '/') {
                            type = 2;
                        }

                        data = dataName;
                        gotType = true;
                    }

                    String fileType = "";
                    int dotIndex = line2.lastIndexOf('.');
                    if (dotIndex != -1 && dotIndex < line2.length() - 1) {
                        fileType = line2.substring(dotIndex + 1);
                    }

                    if (!gotType) {
                        if (fileType.equalsIgnoreCase("gif")) {
                            type = 7;
                            data = line2;
                        } else if (fileType.equalsIgnoreCase("jpg")) {
                            type = 6;
                            data = line2;
                        } else if (fileType.equalsIgnoreCase("html")) {
                            type = 8;
                            data = line2;
                        } else {
                            type = 1;
                            data = line2;
                        }
                        gotType = true;
                    }

                    Key key = new Key(line1, type);
                    Record record = new Record(key, data);
                    bstDict.put(record);
                    bst.insert(bst.getRoot(), record);

                    gotType = false;
                    line1 = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                System.out.println("Error reading the input");
            } catch (DictionaryException e) {
                throw new RuntimeException(e);
            }
        }

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        String line = keyboard.readLine();

        while (!line.equals("exit")) {
            String[] parts = line.split("\\s+", 2);
            String command = parts[0];
            String word = (parts.length > 1) ? parts[1] : "";

            if (command.equals("define")) {
                Key wKey = new Key(word, 1);
                Record wRecord = bstDict.get(wKey);

                if (wRecord != null) {
                    System.out.println(wRecord.getDataItem());
                } else {
                    System.out.println("The word " + word + " is not in the dictionary");
                }
            } else if (command.equals("translate")) {
                Key wKey = new Key(word, 2);
                Record wRecord = bstDict.get(wKey);

                if (wRecord != null) {
                    System.out.println(wRecord.getDataItem());
                } else {
                    System.out.println("There is no definition for the word " + word);
                }
            } else if (command.equals("sound") || command.equals("play") || command.equals("say")) {
                int type = (command.equals("sound")) ? 3 : (command.equals("play")) ? 4 : 5;
                Key wKey = new Key(word, type);
                Record wRecord = bstDict.get(wKey);

                if (wRecord != null) {
                    // Assuming SoundPlayer class exists
                    SoundPlayer soundPlayer = new SoundPlayer();
                    soundPlayer.play(wRecord.getDataItem());
                } else {
                    System.out.println("There is no " + command + " file for " + word);
                }
            } else if (command.equals("show") || command.equals("animate")) {
                int type = (command.equals("show")) ? 6 : 7;
                Key wKey = new Key(word, type);
                Record wRecord = bstDict.get(wKey);

                if (wRecord != null) {
                    // Assuming PictureViewer class exists
                    PictureViewer picViewer = new PictureViewer();
                    picViewer.show(wRecord.getDataItem());
                } else {
                    System.out.println("There is no " + command + " file for " + word);
                }
            } else if (command.equals("browse")) {
                Key wKey = new Key(word, 8);
                Record wRecord = bstDict.get(wKey);

                if (wRecord != null) {
                    // Assuming ShowHTML class exists
                    ShowHTML HTMLViewer = new ShowHTML();
                    HTMLViewer.show(wRecord.getDataItem());
                } else {
                    System.out.println("There is no webpage called " + word);
                }
            } else if (command.equals("delete") || command.equals("add")) {
                String[] partsWithType = line.split("\\s+", 3);
                int type = Integer.parseInt(partsWithType[1]);
                String newWord = partsWithType[2];
                Key wKey = new Key(newWord, type);

                if (command.equals("delete")) {
                    if (bstDict.get(wKey) != null) {
                        bstDict.remove(wKey);
                    } else {
                        System.out.println("No record in the ordered dictionary has key (" + newWord + ", " + type + ").");
                    }
                } else {
                    String data = line.substring(line.indexOf(type) + 2).trim();
                    Record addRecord = new Record(wKey, data);

                    if (bstDict.get(wKey) == null) {
                        bstDict.put(addRecord);
                    } else {
                        System.out.println("A record with the given key (" + newWord + ", " + type + ") is already in the ordered dictionary.");
                    }
                }
            } else if (command.equals("list")) {
                // Collect matches
                StringBuilder matchesBld = new StringBuilder();
                Record record = bstDict.smallest();
                while (record != null) {
                    if (record.getKey().getLabel().startsWith(word)) {
                        matchesBld.append(record.getKey().getLabel()).append(", ");
                    }
                    record = bstDict.successor(record.getKey());
                }
                String matches = matchesBld.toString();

                if (!matches.isEmpty()) {
                    System.out.println(matches.substring(0, matches.length() - 2));
                } else {
                    System.out.println("No label attributes in the ordered dictionary start with prefix " + word);
                }
            } else if (command.equals("first")) {
                Record smallestRecord = bstDict.smallest();
                if (smallestRecord != null) {
                    String label = smallestRecord.getKey().getLabel();
                    int type = smallestRecord.getKey().getType();
                    String data = smallestRecord.getDataItem();
                    System.out.println(label + "," + type + "," + (data.endsWith(".") ? data : data + "."));
                }
            } else if (command.equals("last")) {
                Record largestRecord = bstDict.largest();
                if (largestRecord != null) {
                    String label = largestRecord.getKey().getLabel();
                    int type = largestRecord.getKey().getType();
                    String data = largestRecord.getDataItem();
                    System.out.println(label + "," + type + "," + (data.endsWith(".") ? data : data + "."));
                }
            } else {
                System.out.println("\nInvalid command.");
            }
            line = keyboard.readLine();
        }
    }
}
