// Wuihee 
// 11/23/2023
// CSE 123 
// C3: BrettFeed Quiz
// TA: Heon Jwa

import java.util.*;

/**
 * QuizzTree class uses a Binary Tree to represent the BrettFeed Quiz.
 */
public class QuizTree {

    // Fields
    QuizTreeNode overallRoot;

    /**
     * Instantiate QuizTree from an text file dictating the quiz structure.
     * @param inputFile The input file containing the information for the quiz structure.
     */
    public QuizTree(Scanner inputFile) {
        Queue<QuizTreeNode> binaryTree = new LinkedList<>();
        while (inputFile.hasNextLine()) {
            binaryTree.add(new QuizTreeNode(inputFile.nextLine()));
        }
        overallRoot = buildTree(binaryTree);
    }

    public void takeQuiz(Scanner console) {
        QuizTreeNode node = overallRoot;

        while (!isEndNode(node)) {
            String[] nodeValues = node.value.split("/");
            String left = nodeValues[0];
            String right = nodeValues[1];
            System.out.print("Do you prefer " + left + " or " + right + "? ");

            String userChoice = console.nextLine();
            if (userChoice.equals(left)) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        System.out.println("Your result is: " + node.value.substring(4));
    }

    public void printTree() {
        printTree(overallRoot);
        System.out.println();
    }

    private void printTree(QuizTreeNode node) {
        System.out.print(node.value + ", ");
        if (node.left != null) {
            printTree(node.left);
        }
        if (node.right != null) {
            printTree(node.right);
        }
    }

    /**
     * Recursive helper method to build the quiz's binary tree given a pre-order ArrayList of
     * QuizTreeNodes.
     * 
     * @param binaryTree A Queue of QuizTreeNodes representing the Binary Tree in pre-order
     *                   traversal.
     */
    private QuizTreeNode buildTree(Queue<QuizTreeNode> binaryTree) {
        if (!binaryTree.isEmpty()) {
            QuizTreeNode node = binaryTree.poll();

            if (isEndNode(node)) {
                return node;
            }

            node.left = buildTree(binaryTree);
            node.right = buildTree(binaryTree);
            return node;
        }

        return null;
    }

    /**
     * Helper method to check if node is an end node.
     * 
     * @param node Node to check.
     * @return True if the node is an end node, otherwise false.
     */
    private boolean isEndNode(QuizTreeNode node) {
        return (node.value.indexOf(":") != -1);
    }

    /**
     * QuizTreeNode class represents a node in the binary tree data structure that represents the
     * quiz.
     */
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

        /**
         * Construct an empty QuizTreeNode.
         */
        public QuizTreeNode() {
            this(null);
        }
    }
}
