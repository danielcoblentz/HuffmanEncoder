import java.io.*;
import java.util.*;


//plan:
//1) create hashmap with letter as keys and frequency as value
public class huffman {
    public static void main(String[] args) {
        String filePath = "test/msgspec.txt";  // path to demo file (change as needed for different files)
        Map<Character, Integer> frequencyTable = new HashMap<>();

        //read input and load into hashmap
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int ch;
            while ((ch = br.read()) != -1) { 
                char letter = (char) ch;
                if (letter == ' ') letter = '-';  // mapping space to '-'
                else if (letter == '\n') letter = '!'; // new line mapped to '!'
                frequencyTable.put(letter, frequencyTable.getOrDefault(letter, 0) + 1);
            }
        } catch (IOException e) {
            System.out.println("error check the file path");
        }

        
        // Print frequency table to terminal
        System.out.println("Frequency table:");
        for (Map.Entry<Character, Integer> entry : frequencyTable.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
