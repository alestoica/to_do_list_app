package validator;

import entities.Task;

public class TaskValidator {
    public static boolean validate(Task task) {
        if (task == null) {
            return false;
        }

        if (task.getDescription() == null || task.getDescription().trim().isEmpty()) {
            return false;
        }

        return true;
    }
}
