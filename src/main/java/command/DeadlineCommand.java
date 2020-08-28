package command;

import exception.InvalidDateTimeFormatException;
import exception.InvalidInputException;
import exception.InvalidSaveFileException;
import logic.Storage;
import logic.Ui;
import tasks.Deadlines;
import tasks.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Adds the deadline entry that the user input to the
 * Arraylist of logic.Duke.
 */
public class DeadlineCommand extends Command{

    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public DeadlineCommand(String input) {
        super(input);
    }

    /**
     * Add deadline entry to the Arraylist.
     *
     * @param tasks List of tasks given.
     * @param ui Handles the output to print.
     * @param storage Writes the save file.
     * @throws InvalidDateTimeFormatException If input does not follow format specified.
     * @throws InvalidInputException If the input for the delete is incorrect.
     * @throws InvalidSaveFileException If there is an issue writing the save file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateTimeFormatException,
            InvalidInputException, InvalidSaveFileException {
        if (super.input.length() <= 9) {
            throw new InvalidInputException("\t☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] splitWord = super.input.split("/");
        String desc = splitWord[0].substring(9,splitWord[0].length()-1);
        String deadline = splitWord[1].substring(3);
        Deadlines task;
        try {
            task = new Deadlines(desc, LocalDateTime.parse(deadline,dtf));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException("\tDeadline input must follow a certain format: yyyy-mm-dd HH:mm " +
                    "e.g. 2020-08-23 16:45");
        }
        tasks.addTask(task);
        ui.printOutput("\tGot it. I've added this task:\n" + "\t" + task.toString() +
                "\n\tNow you have " + tasks.getTasks().size() + " tasks in the list.");
        storage.saveFile(tasks.getTasks());
    }

    /**
     * Lets the main logic know that it cannot exit.
     * @return False to prevent loop from exiting.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}