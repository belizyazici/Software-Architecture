import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

//============================================================================
//Name        : ObserverPattern.java
//============================================================================
//The classes and/or objects participating in this pattern are:
//    1. Subject  (Bell)
//        . Knows its observers. Any number of Observer objects may observe a subject.
//        . Provides an interface for attaching and detaching Observer objects.
//    2. ConcreteSubject  (SchoolBell)
//        . Stores state of interest to ConcreteObserver and sends a notification to its
//        . Observers when its state changes.
//    3. Observer  (Room)
//        . Defines an updating interface for objects that should be notified
//        . of changes in a subject.
//    4. ConcreteObserver  (Classroom)
//        . Maintains a reference to a ConcreteSubject object
//        . Stores state that should stay consistent with the subject's
//        . Implements the Observer updating interface to keep its state
//          consistent with the subject's

//'Subject' ==> Bell
abstract class Bell {
    protected Principal p;
    protected int time;

    Bell(Principal p){
        this.p = p;
    }
    //Register the Observers
    public void Attach (Room room) {
        p.register(this, room);
    }
    //Unregister from the list of Observers.
    public void Detach (Room room) {
        p.unregister(this, room);
    }
    //Notify the Observers.
    public void Notify() {
        p.NotifyPrincipal(this);
    }

    abstract public int getTime();
    abstract public void setTime(int value);
}

//'ConcreteSubject' ==> SchoolBell

class SchoolBell extends Bell {
    SchoolBell(Principal p) {
        super(p);
    }

    public int getTime() { return time; }
    public void setTime(int value) {
        time = value;
        Notify();
    }
}

//'Observer'  ==> Room

interface Room {
    public void Update(Bell bell);
}

//'ConcreteObserver' ==> Classroom

class Classroom implements Room {
    private String name;
    private int time;

    // Constructor
    public Classroom(String name) {
        this.name = name;
    }

    public void Update(Bell bell) {
        time = bell.getTime();
        System.out.println("Notified " + name + " at " + time);
        Counter.increaseCounter();
    }
}

// Counter class to count updates

class Counter {
    private static int updateCounter = 0;

    public static void increaseCounter() {
        updateCounter++;
    }

    public static int getUpdateCount() {
        return updateCounter;
    }
}

// Principal manages the registration and notification of rooms

class Principal {
    private ArrayList<Room> c20_list;
    private ArrayList<Room> c50_list;
    private ArrayList<Room> o20;
    private ArrayList<Room> o50;

    public Principal() {
        this.c20_list = new ArrayList<>();
        this.c50_list = new ArrayList<>();
        this.o20 = new ArrayList<>();
        this.o50 = new ArrayList<>();
    }

    public void add(Room room, int time) {
        if (time == 20) c20_list.add(room);
        else if (time == 50) c50_list.add(room);
    }

    public void register(Bell b, Room room) {
        if (c20_list.contains(room)) o20.add(room);
        else if (c50_list.contains(room)) o50.add(room);
    }

    public void unregister(Bell b, Room room) {
        o20.remove(room);
        o50.remove(room);
    }

    public void NotifyPrincipal(Bell b) {
        int time = b.getTime();
        ArrayList<Room> toNotify;
        if (time == 20) toNotify = o20;
        else if (time == 50) toNotify = o50;
        else return;

        for (Room room : toNotify) {
            room.Update(b);
        }
    }
}

//MainApp test application
public class Lab_10 {
    public static void main(String[] args) throws InterruptedException {
        Principal principal = new Principal();

        Room s = new Classroom("c105");
        Room b = new Classroom("c205");

        // Register classrooms with the principal for different bell times
        principal.add(s, 20);
        principal.add(b, 50);

        // Create a school bell and attach classrooms
        Bell schoolBell = new SchoolBell(principal);
        schoolBell.Attach(s);
        schoolBell.Attach(b);

        // Change time, which notifies classrooms
        schoolBell.setTime(20);
        schoolBell.setTime(50);

        Room c1 = new Classroom("A1");
        principal.add(c1, 20);

        Bell bell = new SchoolBell(principal);
        bell.Attach(c1);

        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        while (num != 0) {
            bell.setTime(20);
            TimeUnit.SECONDS.sleep(1);
            bell.setTime(50);
            TimeUnit.SECONDS.sleep(1);
            num = in.nextInt();
        }

        bell.Detach(c1);
        bell.setTime(20);
        TimeUnit.SECONDS.sleep(1);
        bell.setTime(50);
        TimeUnit.SECONDS.sleep(1);

        System.out.println("Number of updates: " + Counter.getUpdateCount());
        in.close();
    }
}
