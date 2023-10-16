// Wuihee 
// 10/06/2023
// CSE 123 
// C0: Abstract Strategy Game
// TA: Heon Jwa

import java.util.*;

/**
 * A task that tests endurance by requiring contestants to lift a huge amount of weight.
 */
public class StrengthTask extends Task {
    
    // Fields
    private List<String> actionOptions;
    private int weightRemaining;
    private int strengthRemaining;

    /**
     * Constructor which instantiates a StrengthTask.
     * 
     * @param weight The amount of weight needed to be lifted to complete the task.
     * @param strengthRemaining The amount of strength remaining measured in kg.
     * @param description A text description of the task.
     */
    public StrengthTask(int weight, int strengthRemaining, String description) {
        super(description);
        this.weightRemaining = weight;
        this.strengthRemaining = strengthRemaining;
        actionOptions = new ArrayList<>(Arrays.asList("lift", "rest"));
    }

    /**
     * Returns a string representation of the task and remaining weight and strength.
     * 
     * @return A String containing the description and the remaining weight and strength.
     */
    @Override
    public String getDescription() {
        return ("""
                %s
                Weight Remaining: %dkg
                Strength Remaining: %dkg
                """).formatted(super.getDescription(), weightRemaining, strengthRemaining);
    }

    /**
     * Returns a list of actions that may be attempted to complete this StrengthTask.
     * Valid actions include lift, rest.
     * 
     * @return the list of valid actions for the StrengthTask.
     */
    public List<String> getActionOptions() {
        return actionOptions;
    }

    /**
     * Returns whether or not this StrengthTask has been completed. The task has been completed
     * when all the weight remaining has been lifted.
     * 
     * @return true if the task has been completed, false otherwise.
     */
    public boolean isComplete() {
        return weightRemaining <= 0;
    }

    /**
     * Attempts to take an action to work towards completing the StrengthTask.
     * 
     * @param action the action to be attempted.
     * @return true if the action is the same type of action required to complete part of the
     *         StrengthTask, false otherwise.
     * @throws IllegalArgumentException if the action attempted is not a valid action for this task
     *         (as specified by getActionOptions())
     * @see getActionOptions
     */
    public boolean takeAction(String action) {
        if (actionOptions.indexOf(action) == -1) {
            throw new IllegalArgumentException("**Invalid action: " + action + "**");
        } else if (action.equals("lift")) {
            if (strengthRemaining == 0) {
                return false;
            } else {
                weightRemaining -= strengthRemaining;
                strengthRemaining = 0;
                return true;
            }
        } else {
            strengthRemaining += 50;
            return true;
        }
    }
}
