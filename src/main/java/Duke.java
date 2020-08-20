import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static List<Task> toDoList = new ArrayList<Task>();
    public static String seperator = "\t____________________________________________________________";

    public static void listTask() {
        System.out.println(seperator);
        if(toDoList.size() == 0) {
            System.out.println("\tList is empty! Start adding some tasks");
        } else {
            System.out.println("\t Here are the tasks in your list:");
            for (int i = 1; i <= toDoList.size(); i++) {
                Task current = toDoList.get(i - 1);
                System.out.println("\t" + i + "." + current.toString());
            }
        }
        System.out.println(seperator);
    }

    public static void completeTask(String newLine) throws InvalidInputException {
        if(newLine.length() <= 5) {
            throw new InvalidInputException("\t☹ OOPS!!! Please specify which task you want to complete!");
        }
        int completed = Integer.parseInt(newLine.substring(5));
        Task current = toDoList.get(completed - 1);
        current.completeTask();
        System.out.println(seperator);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t[" + current.getIcon() + "] " + current.taskDesc);
        System.out.println();
    }

    private static void deadlineTask(String newLine) throws InvalidInputException {
        if(newLine.length() <= 9) {
            throw new InvalidInputException("\t☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] splitWord = newLine.split("/");
        Deadlines task = new Deadlines(splitWord[0].substring(9),splitWord[1]);
        toDoList.add(task);
        System.out.println(seperator);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t"+task.toString());
        System.out.println("\tNow you have " + toDoList.size() + " tasks in the list.");
        System.out.println(seperator);
    }
    private static void eventTask(String newLine) throws  InvalidInputException{
        if(newLine.length() <= 6) {
            throw new InvalidInputException("\t☹ OOPS!!! The description of an event cannot be empty.");
        }
        String[] splitWord = newLine.split("/");
        Events task = new Events(splitWord[0].substring(6),splitWord[1]);
        toDoList.add(task);
        System.out.println(seperator);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t"+task.toString());
        System.out.println("\tNow you have " + toDoList.size() + " tasks in the list.");
        System.out.println(seperator);
    }

    private static void todoTask(String newLine) throws InvalidInputException {
        if(newLine.length() <= 5) {
            throw new InvalidInputException("\t☹ OOPS!!! The description of a todo cannot be empty.");
        }
        ToDos task = new ToDos(newLine.substring(5));
        toDoList.add(task);
        System.out.println(seperator);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t"+task.toString());
        System.out.println("\tNow you have " + toDoList.size() + " tasks in the list.");
        System.out.println(seperator);
    }

    public static void deleteTask(String newLine) throws InvalidInputException{
        if(newLine.length() <= 7) {
            throw new InvalidInputException("\t☹ OOPS!!! The description of a delete operation cannot be empty / invalid index.");
        }
        int index = Integer.parseInt(newLine.substring(7));
        if( index >= toDoList.size()) {
            throw new InvalidInputException("\t☹ OOPS!!! The description of a delete operation cannot be empty / invalid index.");
        }
        Task task = toDoList.get(index-1);
        toDoList.remove(index-1);
        System.out.println(seperator);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t"+task.toString());
        System.out.println("\tNow you have " + toDoList.size() + " tasks in the list.");
        System.out.println(seperator);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(seperator);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println(seperator);
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            try {
                String newLine = sc.nextLine();
                if (newLine.equals("bye")) {
                    System.out.println(seperator);
                    System.out.println("\tBye. Hope to see you again soon!");
                    System.out.println(seperator);
                    break;
                } else if (newLine.equals("list")) {
                    listTask();
                } else if (newLine.length() >= 4 && newLine.substring(0, 4).equals("done")) {
                    completeTask(newLine);
                } else if (newLine.length() >= 4 && newLine.substring(0, 4).equals("todo")) {
                    todoTask(newLine);
                } else if (newLine.length() >= 8 && newLine.substring(0, 8).equals("deadline")) {
                    deadlineTask(newLine);
                } else if (newLine.length() >= 5 && newLine.substring(0, 5).equals("event")) {
                    eventTask(newLine);
                } else if (newLine.length() >= 6 && newLine.substring(0, 6).equals("delete")) {
                    deleteTask(newLine);
                } else {
                    throw new InvalidInputException("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (InvalidInputException e) {
                System.out.println(seperator);
                System.out.println(e.getMessage());
                System.out.println(seperator);
            }
        }
    }


}
