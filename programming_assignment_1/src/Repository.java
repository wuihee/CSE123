// Wuihee 
// 10/31/2023
// CSE 123 
// P1: Mini-Git
// TA: Heon Jwa

import java.util.*;
import java.text.SimpleDateFormat;

public class Repository {

    // Fields
    private String name;
    public Commit head;  // IS THIS ALLOWED?????????????????????????????????????????????????????????????????????????????????????????
    private int size;

    /**
     * Constructor for Repository class.
     * 
     * @param name The name of the repository.
     */
    public Repository(String name) {
        this.name = name;
        this.size = 0;
    }

    /**
     * Create a new commit in the repository which becomes the new head.
     * 
     * @param message The message of the new commit.
     * @return The ID of the new commit.
     */
    public String commit(String message) {
        head = new Commit(message, head);
        size++;
        return getRepoHead();
    }

    /**
     * Retrieve the ID of the head commit.
     * 
     * @return The ID of the current head, or null if the head is null.
     */
    public String getRepoHead() {
        if (head == null) {
            return null;
        }
        return head.id;
    }

    /**
     * Retrieve size of the repository.
     * 
     * @return the number of commits in the repository.
     */
    public int getRepoSize() {
        return size;
    }

    /**
     * Returns a String representation of the current repository.
     * 
     * @return <name> - Current head: <head>, or <name> - No commits if no commits.
     */
    @Override
    public String toString() {
        if (getRepoHead() == null) {
            return name + " - No commits";
        }
        return name + " - Current head: " + head.toString();
    }

    /**
     * Check if a commit with the targetId is in the repository.
     * 
     * @param targetId The ID of the commit we wish to find.
     * @return Return true if the commit with ID targetId is in the repository, false if not.
     */
    public boolean contains(String targetId) {
        Commit current = head;

        while (current != null) {
            if (current.id.equals(targetId)) {
                return true;
            }
            current = current.past;
        }

        return false;
    }

    /**
     * Remove the commit with ID targetId from this repository, maintaining the rest of the
     * history.
     * 
     * @param targetId The ID of the commit we wish to remove.
     * @return true if the commit was successfully dropped, and false if there is no commit
     *         that matches the given ID in the repository.
     */
    public boolean drop(String targetId) {
        Commit sentinel = new Commit("", head);
        Commit current = sentinel;

        while (current.past != null) {
            if (current.past.id.equals(targetId)) {
                current.past = current.past.past;
                head = sentinel.past;
                return true;
            }
            current = current.past;
        }
        return false;
    }

    /**
     * Return a string consisting of the String representations of the most recent n commits
     * in this repository, with the most recent first. If there are fewer than n commits,
     * return everything.
     * 
     * @param n The number of commits to display.
     * @throws IllegalArgumentException if n is less than 0.
     * @return A String containing list of n commits separated by newlines.
     */
    public String getHistory(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }

        Commit current = head;
        String result = "";
        int i = 0;

        while (current != null && i < n) {
            result += current.toString() + "\n";
            current = current.past;
            i++;
        }

        return result;
    }

    /**
     * Combines another repository into the current one such that chrnonological history is
     * preserved.
     * 
     * @param other The other repository to combine.
     */
    public void synchronize(Repository other) {
        Commit sentinel1 = new Commit("", head);
        Commit sentinel2 = new Commit("", other.head);
        Commit current1 = sentinel1;
        Commit current2 = sentinel2;
        Commit temp;

        while (current1.past != null && current2.past != null) {
            if (current2.past.timeStamp > current1.past.timeStamp) {
                temp = current1.past;
                current1.past = current2.past;
                current2 = new Commit("", current2.past.past);
                current1.past.past = temp;
                current1 = current1.past;
            } else {
                current1 = current1.past;
            }
        }

        if (current2.past != null) {
            current1.past = current2.past;
        }

        other.head = null;
        head = sentinel1.past;
    }

    /**
     * DO NOT MODIFY
     * A class that represents a single commit in the repository.
     * Commits are characterized by an identifier, a commit message,
     * and the time that the commit was made. A commit also stores
     * a reference to the immediately previous commit if it exists.
     *
     * Staff Note: You may notice that the comments in this 
     * class openly mention the fields of the class. This is fine 
     * because the fields of the Commit class are public. In general, 
     * be careful about revealing implementation details!
     */
    public class Commit {

        private static int currentCommitID;

        /**
         * The time, in milliseconds, at which this commit was created.
         */
        public final long timeStamp;

        /**
         * A unique identifier for this commit.
         */
        public final String id;

        /**
         * A message describing the changes made in this commit.
         */
        public final String message;

        /**
         * A reference to the previous commit, if it exists. Otherwise, null.
         */
        public Commit past;

        /**
         * Constructs a commit object. The unique identifier and timestamp
         * are automatically generated.
         * @param message A message describing the changes made in this commit.
         * @param past A reference to the commit made immediately before this
         *             commit.
         */
        public Commit(String message, Commit past) {
            this.id = "" + currentCommitID++;
            this.message = message;
            this.timeStamp = System.currentTimeMillis();
            this.past = past;
        }

        /**
         * Constructs a commit object with no previous commit. The unique
         * identifier and timestamp are automatically generated.
         * @param message A message describing the changes made in this commit.
         */
        public Commit(String message) {
            this(message, null);
        }

        /**
         * Returns a string representation of this commit. The string
         * representation consists of this commit's unique identifier,
         * timestamp, and message, in the following form:
         *      "[identifier] at [timestamp]: [message]"
         * @return The string representation of this collection.
         */
        @Override
        public String toString() {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(timeStamp);

            return id + " at " + formatter.format(date) + ": " + message;
        }

        /**
        * Resets the IDs of the commit nodes such that they reset to 0.
        * Primarily for testing purposes.
        */
        public static void resetIds() {
            Commit.currentCommitID = 0;
        }
    }
}
