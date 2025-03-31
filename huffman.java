import java.io.*;
import java.util.*;

public class huffman {
    // class for HT nodes 
    static class Node {
        char ch;
        int freq;
        Node left, right;
        Node(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
        Node(int freq, Node left, Node right) {
            this.ch = '\0';  // non leaf node
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        boolean isLeaf() {
            return left == null && right == null;
        }
    }


    public static void main(String[] args) {
        //part 1: build frequency table
        String filePath = "test/filetoencode.txt";  // path to input file(change this as needed for testing other inputs)

        // Build frequency table
        Map<Character, Integer> frequencyTable = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int ch;
            while ((ch = br.read()) != -1) {
                char letter = (char) ch;

                if (letter == '\r') continue;          // handle windows line endings
                if (letter == ' ') letter = '-';       // space becomes '-'
                else if (letter == '\n') letter = '!'; // newline becomes '!'

                frequencyTable.put(letter, frequencyTable.getOrDefault(letter, 0) + 1);
            }
        } catch (IOException e) {
            System.out.println("problem reading file: " + e.getMessage()); // error logging
            return;
        }
        // + to mark end of file(should only be one per table)
        frequencyTable.put('+', frequencyTable.getOrDefault('+', 0) + 1);

        // print frequency table
        System.out.println("frequency table:");
        for (Map.Entry<Character, Integer> entry : frequencyTable.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }



        //Part 2: PQ / huff tree implementation -----
        // create HT
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.freq));
        for (Map.Entry<Character, Integer> entry : frequencyTable.entrySet()) {
            pq.offer(new Node(entry.getKey(), entry.getValue()));
        }
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            pq.offer(new Node(left.freq + right.freq, left, right));
        }
        Node root = pq.poll(); 

        // generate Huffman codes
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);

        // Print huffman codes to suer
        System.out.println("\nhuffman codes:");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        //print encoded message to user using huffman codes above
        String encodedData = encodeFile(filePath, huffmanCodes);
        System.out.println("\nencoded message:");
        System.out.println(encodedData);
    }



    // helper function to recursively build the code table
    public static void generateCodes(Node node, String code, Map<Character, String> huffmanCodes) {
        if (node == null) return;

        if (node.isLeaf()) {
            // handle single char edge case
            huffmanCodes.put(node.ch, code.length() > 0 ? code : "0");
        } else {
            generateCodes(node.left, code + "0", huffmanCodes);
            generateCodes(node.right, code + "1", huffmanCodes);
        }
    }



    // method to read the file again transforms characters and appends huff codes
    public static String encodeFile(String filePath, Map<Character, String> huffmanCodes) {
        StringBuilder encoded = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int ch;
            while ((ch = br.read()) != -1) {
                char letter = (char) ch;
//same checks as before in pt1
                if (letter == '\r') continue;
                if (letter == ' ') letter = '-';       // space becomes -
                else if (letter == '\n') letter = '!'; // newline becomes !

                encoded.append(huffmanCodes.get(letter));
            }
        } catch (IOException e) {
            System.out.println("cannot encode file (line 119): " + e.getMessage()); // error logging
        }
        encoded.append(huffmanCodes.get('+'));
        return encoded.toString();
    }
}
