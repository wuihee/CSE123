// Wuihee 
// 11/23/2023
// CSE 123 
// C3: BrettFeed Quiz
// TA: Heon Jwa

import java.util.*;

public class QuizTree {
    public QuizTree(Scanner inputFile) {

    }

    public static class QuizTreeNode {

        // Fields
        String value;
        QuizTreeNode left;
        QuizTreeNode right;

        /**
         * Construct a QuizTreeNode object with a value and its left and right children.
         * 
         * @param value The value of the node.
         * @param left The left child of the node.
         * @param right The right child of the node.
         */
        public QuizTreeNode(String value, QuizTreeNode left, QuizTreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        /**
         * Construct a QuizTreeNode object with a value and no children.
         * 
         * @param value The value of the node.
         */
        public QuizTreeNode(String value) {
            this(value, null, null);
        }
    }
}
