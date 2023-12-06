// Wuihee 
// 12/06/2023
// CSE 123 
// P3: Huffman Coding
// TA: Heon Jwa

import java.util.*;
import java.io.*;

// TODO add more commenting.
/**
 * HuffmanCode class which is used for compressing data using Huffman Coding.
 */
public class HuffmanCode {

    // Fields
    HuffmanNode root;
    
    /**
     * Instantiate HuffmanCode with an array of character frequencies.
     * 
     * @param frequencies Where frequencies[i] is the count of the character with ASCII value i.
     */
    public HuffmanCode(int[] frequencies) {
        root = encode(frequencies);
    }

    /**
     * Initialize HuffmanCode by reading a previously constructed code from a .code file.
     * @param input Scanner object to read .code file.
     */
    public HuffmanCode(Scanner input) {
        root = read(input);
    }

    /**
     * Create a huffman encoding from the given frequencies.
     * 
     * @return The root node of the Huffman tree.
     */
    private HuffmanNode encode(int[] frequencies) {
        // Initialize queue and add all nodes to queue.
        Queue<HuffmanNode> queue = new PriorityQueue<>();
        for (int character = 0; character < frequencies.length; character++) {
            queue.add(new HuffmanNode(frequencies[character], character));
        }

        while (queue.size() > 1) {
            HuffmanNode leftNode = queue.poll();
            HuffmanNode rightNode = queue.poll();
            HuffmanNode newNode = new HuffmanNode(
                leftNode, rightNode, leftNode.frequency + rightNode.frequency
            );
            queue.add(newNode);
        }

        return queue.poll();
    }

    /**
     * Read Huffman encoding from given .code file.
     * 
     * @param input Scanner object to .code file.
     * @return The root node of the Huffman tree.
     */
    private HuffmanNode read(Scanner input) {
        root = new HuffmanNode();

        while (input.hasNextInt()) {
            int ascii = input.nextInt();
            int code = input.nextInt();
            addNode(code, ascii, root);
        }

        return root;
    }

    private void addNode(int code, int ascii, HuffmanNode node) {
        
    }

    /**
     * Stores the current Huffman Code to the given output stream, where each character encoding
     * is represented with 2 lines - the first line being the ASCII code of the character and the
     * second line being the Huffman encoding.
     * 
     * @param output
     */
    public void save(PrintStream output) {
        save(output, root, "");
    }

    private void save(PrintStream output, HuffmanNode node, String encoding) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                output.println(node.value);
                output.println(encoding);
            } 
            if (node.left != null) {
                save(output, node.left, encoding + "0");
            }
            if (node.right != null) {
                save(output, node.right, encoding + "1");
            }
        }
    }

    /**
     * HuffmanNode class representing a node in the Huffman tree.
     */
    private static class HuffmanNode implements Comparable<HuffmanNode> {

        // Fields
        HuffmanNode left;
        HuffmanNode right;
        int frequency;
        int value;

        /**
         * Instantiate HuffmanNode with a frequency and value.
         * 
         * @param left The left child.
         * @param right The right child.
         * @param frequency The frequency which the given value occurs.
         * @param value The ASCII value of the given character.
         */
        public HuffmanNode(HuffmanNode left, HuffmanNode right, int frequency, int value) {
            this.left = left;
            this.right = right;
            this.frequency = frequency;
            this.value = value;
        }

        /**
         * Instantiate an combined HuffmanNode with no value.
         * 
         * @param left The left child.
         * @param right The right child.
         * @param frequency The combined frequency of two other nodes.
         */
        public HuffmanNode(HuffmanNode left, HuffmanNode right, int frequency) {
            this(left, right, frequency, 0);
        }

        /**
         * Instantiate a HuffmanNode leaf with no children.
         * 
         * @param frequency The frequency which the given value occurs.
         * @param value The ASCII value of the given character.
         */
        public HuffmanNode(int frequency, int value) {
            this(null, null, frequency, value);
        }

        /**
         * Initialize an empty HuffmanHode.
         */
        public HuffmanNode() {
            this(null, null, 0, 0);
        }

        @Override
        public int compareTo(HuffmanNode other) {
            return frequency - other.frequency;
        }
    }
}
