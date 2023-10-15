// Wuihee 
// 10/06/2023
// CSE 123 
// C0: Abstract Strategy Game
// TA: Heon Jwa

import java.util.*;

/**
 * A task that tests endurance by requiring contestants to repeat a single action many times in a
 * row.
 */
public class EnduranceTask extends Task{

    // Fields
    List<String> actionOptions;
    String type;
    int duration;
    int successfulActions;

    /**
     * Constructor which instantiates an EnduranceTask.
     * 
     * @param type The action required to complete the task.
     * @param duration The number of times the action must be taken to complete the task.
     * @param description A text description of the task.
     */
    public EnduranceTask(String type, int duration, String description) {
        super(description);
        this.type = type;
        this.duration = duration;
        successfulActions = 0;
        actionOptions = new ArrayList<>(Arrays.asList("jump", "run", "swim", "crawl", "climb"));
    }

    /**
     * Returns a list of actions that may be attempted to complete this EnduranceTask.
     * Valid actions include jump, run, swim, crawl, climb.
     * 
     * @return the list of valid actions for the EnduranceTask.
     */
    public List<String> getActionOptions() {
        return actionOptions;
    }

    /**
     * Returns whether or not this EnduranceTask has been completed. The task has been completed
     * when the required action is performed specified duration number of times.
     * 
     * @return true if the task has been completed, false otherwise.
     */
    public boolean isComplete() {
        return successfulActions >= duration;
    }

    /**
     * Attempts to take an action to work towards completing the EnduranceTask.
     * 
     * @param action the action to be attempted.
     * @return true if the action is the same type of action required to complete part of the
     *         EnduranceTask, false otherwise.
     * @throws IllegalArgumentException if the action attempted is not a valid action for this task
     *         (as specified by getActionOptions())
     * @see getActionOptions
     */
    public boolean takeAction(String action) {
        if (actionOptions.indexOf(action) == -1) {
            throw new IllegalArgumentException("**Invalid action: " + action + "**");
        } else if (action != type) {
            return false;
        } else {
            successfulActions++;
            return true;
        }
    }
}
