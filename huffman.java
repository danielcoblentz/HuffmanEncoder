import java.io.*;
import java.util.*;

public class huffman {
    public static void main(String[] args) {
        String filePath = "test/msgspec.txt";  // link to demo file (change path as needed for other files)
        Map<Character, Integer> frequencyTable = new HashMap<>();


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int ch;
            while ((ch = br.read()) != -1) {  // read character by character in the file
                char letter = (char) ch;

                if (letter == '\r') continue;  // seperate case in Windows line endings it counts as a seperate charcter

                if (letter == ' ') letter = '-';  // Mapping space to '-'
                else if (letter == '\n') letter = '!'; // Map new line to '!'

                // Update frequency table
                frequencyTable.put(letter, frequencyTable.getOrDefault(letter, 0) + 1);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // Increment '+' at the end of the file
        frequencyTable.put('+', frequencyTable.getOrDefault('+', 0) + 1);

        // Print frequency table
        System.out.println("Frequency table:");
        for (Map.Entry<Character, Integer> entry : frequencyTable.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
