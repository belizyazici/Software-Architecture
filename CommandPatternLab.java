import java.util.*;

interface Task { // Command
    void execute();
    void unexecute();
}

class ConcreteTask implements Task { // Concrete Command
    private CPU cpu;
    private String previousColor;

    public ConcreteTask(CPU cpu) {
        this.cpu = cpu;
    }

    @Override
    public void execute() {
        // Save previous color before executing the task
        previousColor = cpu.getScreenColor();
        cpu.makeBlue();
    }

    @Override
    public void unexecute() {
        // Revert back to previous color
        cpu.setScreenColor(previousColor);
    }

    public String getPreviousColor() {
        return previousColor;
    }
}

class MacroTask implements Task{
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task){
        tasks.add(task);
    }

    @Override
    public void execute() {

    }

    @Override
    public void unexecute() {

    }
}

interface PC {
    void makeGreen();
    void makeRed();
    void makeBlue();
    void makeYellow();
    String getScreenColor();
    void setScreenColor(String color);
}

class CPU implements PC { // Receiver
    private String screenColor;

    public CPU(String screenColor) {
        this.screenColor = screenColor;
    }

    public void makeGreen() {
        this.screenColor = "GREEN";
        System.out.println("The screen now shows " + screenColor);
    }

    public void makeRed() {
        this.screenColor = "RED";
        System.out.println("The screen now shows " + screenColor);
    }

    public void makeBlue() {
        this.screenColor = "BLUE";
        System.out.println("The screen now shows " + screenColor);
    }

    public void makeYellow() {
        this.screenColor = "YELLOW";
        System.out.println("The screen now shows " + screenColor);
    }

    @Override
    public String getScreenColor() {
        return screenColor;
    }

    @Override
    public void setScreenColor(String color) {
        this.screenColor = color;
    }
}

class TaskScheduler { // Invoker
    private Queue<Task> tasksQueue = new LinkedList<>();
    private Stack<String> screenColorStack = new Stack<>();

    void performTask(Task task) {
        task.execute();
        tasksQueue.add(task);
        if (task instanceof ConcreteTask) {
            ConcreteTask concreteTask = (ConcreteTask) task;
            screenColorStack.push(concreteTask.getPreviousColor());
        }
    }

    void undoLastTask() {
        if (!tasksQueue.isEmpty()) {
            Task lastTask = tasksQueue.poll();
            lastTask.unexecute();
            if (lastTask instanceof ConcreteTask) {
                screenColorStack.pop();
            }
        } else {
            System.out.println("No tasks to undo.");
        }
    }

    void run() {
        System.out.println("Running tasks:");
        for (Task task : tasksQueue) {
            task.execute();
        }
    }

    Stack<String> getScreenColorStack() {
        return screenColorStack;
    }
}

class OS { // Client
    void prepare() {
        // Create objects and set the scene
        CPU cpu = new CPU("BLACK");
        TaskScheduler scheduler = new TaskScheduler();

        // Add tasks to the Task Scheduler
        Task task1 = new ConcreteTask(cpu);
        Task task2 = new ConcreteTask(cpu);

        scheduler.performTask(task1);
        scheduler.performTask(task2);

        // Undo the last task
        scheduler.undoLastTask();

        // Run tasks on the CPU
        scheduler.run();
    }
}

public class CommandPatternLab {
    public static void main(String[] args) {
        OS os = new OS();
        os.prepare();
    }
}
