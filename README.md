# HuffmanEncoder
This program reads input from a file and loads the letters as keys and their frequencies as values, it will also convert specific items into special characters for example: `-' for space, `.' for period, `!' for new line, and `+' for end-of-message. Then the program prints the results to the terminal for the user to view/verify.

First, we create a hashtable to store the key value pairs. Then, we use the specified file path (which can be changed) to read input character by character while looking for special characters (listed above and in instructions). These values are  loaded into the hashtable initialized earlier.

In part two, the program constructs a huffman tree using the frequency table from Part 1. A priority queue is used to combine the least frequent nodes into a binary tree, where characters that occur more often are closer to the root.

After building the tree, the program recursively generates huffman codes for each character and prints them to the terminal. Characters with higher frequencies get shorter binary codes.

> **Note**: This program uses a two-pass approach: the file is read once to build the frequency table, and again after the codes are generated to perform the actual encoding. This allows it to handle large files  without keeping the entire file content in memory. (this is also possible with a one pass method but it requires more memeory useage on average)

## How to Run:
You can run this program in VS Code by clicking run in the top left after using hte same or modifying the file to encode.
